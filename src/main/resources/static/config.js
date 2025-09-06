const getBackendUrl = () => {
    const hostname = window.location.hostname;
    if (hostname === 'localhost' || hostname === '127.0.0.1') {
        return 'http://localhost:8080';
    } else {
        // TODO: Replace this with the URL of your deployed backend
        return 'https://your-backend-service.com';
    }
};

window.config = {
    backendUrl: getBackendUrl()
};
