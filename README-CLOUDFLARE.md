# Deploying to Cloudflare Pages

This document provides instructions for deploying the static frontend of the Prisoner's Dilemma web application to Cloudflare Pages.

## Prerequisites

- A Cloudflare account.
- The backend application deployed and accessible via a public URL.

## Deployment Steps

1.  **Build the Application:**
    This project is a Spring Boot application that serves a static frontend. The frontend files (`index.html`, `style.css`, `app.js`, `config.js`) are located in the `src/main/resources/static` directory. No special build step is required for the frontend as it's composed of static files.

2.  **Push to GitHub:**
    Ensure your latest code, including the `src/main/resources/static` directory, is pushed to your GitHub repository.

3.  **Create a Cloudflare Pages Project:**
    - Log in to your Cloudflare dashboard.
    - Go to **Workers & Pages** > **Create application** > **Pages** > **Connect to Git**.
    - Select your GitHub repository.

4.  **Configure Build Settings:**
    - **Framework preset:** Select **None**.
    - **Build command:** Leave this blank.
    - **Build output directory:** Set this to `src/main/resources/static`.

5.  **Deploy:**
    - Click **Save and Deploy**.
    - Cloudflare will deploy your static files.

Once deployed, your application will be available at the URL provided by Cloudflare Pages. The frontend will automatically connect to the production backend URL specified in `config.js`.
