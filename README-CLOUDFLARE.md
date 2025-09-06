# Deploying Frontend to Cloudflare Pages

This document provides instructions for deploying the static frontend of the Prisoner's Dilemma web application to Cloudflare Pages.

## Prerequisites

-   A Cloudflare account.
-   The backend application deployed in a Docker container and accessible via a public URL.

## Deployment Steps

1.  **Update Backend URL:**
    Before deploying, open `src/main/resources/static/config.js` and replace the placeholder `https://your-backend-service.com` with the actual public URL of your deployed backend container.

2.  **Push to GitHub:**
    Ensure your latest code is pushed to your GitHub repository.

3.  **Create a Cloudflare Pages Project:**
    -   Log in to your Cloudflare dashboard.
    -   Go to **Workers & Pages** > **Create application** > **Pages** > **Connect to Git**.
    -   Select your GitHub repository.

4.  **Configure Build Settings:**
    -   **Framework preset:** Select **None**.
    -   **Build command:** Leave this blank.
    -   **Build output directory:** Set this to `src/main/resources/static`.

5.  **Deploy:**
    -   Click **Save and Deploy**.

Once deployed, your frontend will be live on Cloudflare Pages and will communicate with your backend container.
