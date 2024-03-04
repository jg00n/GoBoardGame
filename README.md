# Go / Baduk / Wei-Chi Board Game #

This Java-based Object-Oriented Exercise is a work in progress (WIP) as of 3/4/24.

## Components ##
The program consists of the following components, organized by their respective attributes:

- **Board**
- **GameBoardGUI**
- **Piece**
- **Rules**

### Board ###
The Board component is responsible for rendering our Go program. In its current state, the board has a default size, and its properties determine the size during rendering.
> [!NOTE]
> The board currently follows a fixed size. While the original plan was to make it dynamic based on the board's complexity, a static size suffices for now.

As the primary arena for game activity, the board is entirely composed of object pieces. This component handles rendering and board interactions. Initially, a matrix was considered, with the intention of rendering it onto a terminal, but this approach was deemed less intuitive.

### GameBoardGUI ###
This component creates a Swing component that renders a window for our board. It is mainly utilized to refresh the board whenever a piece is placed.

### Piece ###
The Piece is an object determining the type of piece on the board. The board is essentially a matrix composed of these pieces, where a state can be set as a blank space for an empty tile, B for black, and W for white.

### Rules ###
> [!NOTE]
> This section is a work in progress, with only the initial board render released.

The intended functionality is to use a flooding algorithm to determine occupied spaces. The goal is to calculate how many tiles a player can occupy on the board. Two variations of this flood fill are envisioned:

- **Strong Barriers:** Follows the general principle of a flood fill by identifying pieces captured within a territory. Players create a sizeable box around a portion of the field to capture pieces within. A strong barrier is formed when pieces are laid orthogonally around the opponent's pieces.

- **Weak Barriers:** A more complex version allowing barriers with diagonal pieces. While less straightforward than strong barriers, weak barriers can lead to deadlock conditions, where neither player gains an advantage. Examples include dead eyes.
