const getBackendUrl = () => {
    const hostname = window.location.hostname;
    if (hostname === 'localhost' || hostname === '127.0.0.1') {
        return 'http://localhost:8080';
    } else {
        return 'https://prisoners-dilemma-worker.rohitdhiman.workers.dev';
    }
};

window.config = {
    backendUrl: getBackendUrl()
};
