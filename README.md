# # Prisoner's Dilemma Web App

This is a simple web application that allows you to play the Prisoner's Dilemma game against an AI opponent named CLS-7.

## Local Deployment

To run this application locally, you will need to have Java and Maven installed.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/rohitdhiman1/prisoners-dilemma-web-app.git
    cd prisoners-dilemma-web-app
    ```

2.  **Run the application:**
    You can run the application using the Maven Spring Boot plugin:
    ```bash
    mvn spring-boot:run
    ```

3.  **Access the application:**
    Once the application is running, open your web browser and navigate to:
    ```
    http://localhost:8080
    ```

The application will be running in development mode, and you can start playing the game immediately.


This project is a single-page web application that allows a user to play the Prisoner's Dilemma game against a computer opponent. The backend is built with Java and Spring Boot, and the frontend is a simple React application.

## Game Logic

The rules of the game are as follows:

- The user always plays first.
- The computer plays second, using a "Tit for Tat" strategy (it will cooperate on the first move, and then copy the user's previous move for all subsequent turns).
- **Scoring:**
    - If both players cooperate, they each get 3 points.
    - If one player cooperates and the other defects, the defector gets 5 points and the cooperator gets 0.
    - If both players defect, they each get 1 point.
- **Winning:** The first player to reach 20 points wins.
- **Turns:** The game has a maximum of 10 turns. If no one reaches 20 points by the end of the 10th turn, the player with the higher score wins.

    The backend server will start on `http://localhost:8080`.

3.  **Play the Game:**
    Open your web browser and navigate to:
    [http://localhost:8080](http://localhost:8080)

    The game will start automatically. Click "Cooperate" or "Defect" to make your move.

## API Endpoints

The following REST API endpoints are available:

- `POST /api/game/start`: Creates a new game.
- `POST /api/game/{gameId}/play`: Submits a player's move (`COOPERATE` or `DEFECT`).
- `GET /api/game/{gameId}`: Retrieves the current state of a game.
