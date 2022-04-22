const api_url = "http://localhost:8080/api/";

async function getResources(resource) {
    return fetch(api_url + resource).then(response => {
        return response.json()
    })
}

document.getElementById('button').addEventListener('click', () => {
    getResources("customers").then(data => {
        console.log(data)
    })
});

document.addEventListener('DOMContentLoaded', () => {
    getResources("customers").then(customers => {
    })
    getResources("exercises").then(exercises => {
    })
    getResources("workouts").then(workouts => {
    })
})
