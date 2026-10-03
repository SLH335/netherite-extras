{
  description = "Netherite Extras mod development shell";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-26.05";

    git-hooks = {
      url = "github:cachix/git-hooks.nix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };

  outputs =
    { nixpkgs, git-hooks, ... }:
    let
      forAllSystems = nixpkgs.lib.genAttrs [
        "x86_64-linux"
        "aarch64-linux"
      ];

      # set editorconfig-checker as pre-commit hook
      # doubles as `nix flake check` lint
      preCommit = forAllSystems (
        system:
        git-hooks.lib.${system}.run {
          src = ./.;
          hooks.editorconfig-checker.enable = true;
        }
      );
    in
    {
      checks = forAllSystems (system: {
        pre-commit = preCommit.${system};
      });

      devShells = forAllSystems (
        system:
        let
          pkgs = nixpkgs.legacyPackages.${system};

          jdk = pkgs.jdk25;

          # not buildInputs, they only need to be reachable via LD_LIBRARY_PATH
          runtimeLibs =
            with pkgs;
            [
              # Video (SDL3 instead of GLFW for 26.3+)
              vulkan-loader
              libGL
              wayland
              libdecor
              libxkbcommon

              # Hardware probing (oshi)
              systemdLibs

              # Audio
              libpulseaudio

              # Narrator TTS
              flite

              # Gradle
              ncurses
              zlib

              # SDL3 X11 video (unused on Wayland)
              libx11
              libxext
              libxcursor
              libxi
              libxfixes
              libxrandr
              libxrender
              libxscrnsaver

              # GLFW X11 (for <26.3)
              libxinerama
              libxxf86vm
            ];
        in
        {
          default = pkgs.mkShell {
            # editorconfig-checker is supplied by enabledPackages
            inherit (preCommit.${system}) shellHook;
            packages = [ jdk ] ++ preCommit.${system}.enabledPackages;

            JAVA_HOME = jdk.home;

            # niri doesn't advertise fifo-v1, so SDL3 doesn't prefer Wayland.
            # force SDL to use Wayland for the dev client
            SDL_VIDEO_DRIVER = "wayland";
            # driver link must come first
            LD_LIBRARY_PATH = "${pkgs.addDriverRunpath.driverLink}/lib:${pkgs.lib.makeLibraryPath runtimeLibs}";
          };
        }
      );
    };
}
