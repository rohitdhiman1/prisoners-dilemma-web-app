# Deploying to Cloudflare Pages

This document provides instructions for deploying the Prisoner's Dilemma web application to Cloudflare Pages. Since this is a full-stack application with a Java backend and a static frontend, the deployment process involves two main parts:

1.  **Deploying the Spring Boot Backend:** The Java backend needs to be hosted on a service that supports server-side applications (e.g., Render, Heroku, AWS, etc.).
2.  **Deploying the Frontend to Cloudflare Pages:** The static frontend files (HTML, CSS, JavaScript) will be deployed on Cloudflare Pages and configured to communicate with the hosted backend.

---

### Part 1: Deploying the Spring Boot Backend

We'll use Render as an example for deploying the backend, as it offers a free tier for web services.

1.  **Create a Render Account:**
    *   Go to [Render](httpss://render.com/) and sign up for a new account.

2.  **Create a New Web Service:**
    *   From your Render dashboard, click on **"New +"** and select **"Web Service"**.
    *   Connect your GitHub account and select the `prisoners-dilemma-web-app` repository.

3.  **Configure the Web Service:**
    *   **Name:** Give your service a unique name (e.g., `prisoners-dilemma-backend`).
    *   **Region:** Choose a region closest to you.
    *   **Branch:** Select the `main` branch.
    *   **Runtime:** Render should automatically detect a Java environment.
    *   **Build Command:** `mvn clean install`
    *   **Start Command:** `java -jar target/prisoners-dilemma-0.0.1-SNAPSHOT.jar`
    *   **Instance Type:** Select the "Free" plan.

4.  **Deploy:**
    *   Click **"Create Web Service"**. Render will start building and deploying your application. This may take a few minutes.
    *   Once the deployment is complete, Render will provide you with a public URL for your backend (e.g., `https://prisoners-dilemma-backend.onrender.com`). Copy this URL, as you will need it for the next part.

---

### Part 2: Deploying the Frontend to Cloudflare Pages

1.  **Update the Frontend Code:**
    *   Open the `src/main/resources/static/app.js` file in your local repository.
    *   You need to replace the relative API paths with the absolute URL of your deployed backend. Find the following lines:
        ```javascript
        const response = await fetch('/api/game/start', { method: 'POST' });
        ```
        and
        ```javascript
        const response = await fetch(`/api/game/${game.id}/play`, {
        ```
    *   Replace them with your backend URL:
        ```javascript
        const backendUrl = 'https://your-backend-url.onrender.com'; // <-- PASTE YOUR RENDER URL HERE

        // ...

        const response = await fetch(`${backendUrl}/api/game/start`, { method: 'POST' });

        // ...

        const response = await fetch(`${backendUrl}/api/game/${game.id}/play`, {
        ```
    *   Save the file, commit the changes, and push them to your GitHub repository.

2.  **Create a Cloudflare Pages Project:**
    *   Log in to your [Cloudflare dashboard](httpss://dash.cloudflare.com/).
    *   Go to **Workers & Pages** > **Create application** > **Pages** > **Connect to Git**.
    *   Select your `prisoners-dilemma-web-app` repository.

3.  **Configure the Deployment:**
    *   **Project name:** Choose a name for your project.
    *   **Production branch:** Select `main`.
    *   **Build settings:**
        *   **Framework preset:** Select `None`.
        *   **Build command:** Leave this blank.
        *   **Build output directory:** `src/main/resources/static`

4.  **Deploy:**
    *   Click **"Save and Deploy"**. Cloudflare will deploy your static frontend.
    *   Once the deployment is complete, you will get a public URL for your frontend. You can now access your fully deployed Prisoner's Dilemma application!
