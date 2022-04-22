const api_url = "http://localhost:8080/api/";

async function getResources(resource) {
    return fetch(api_url + resource).then(response => {
        return response.json()
    })
}

document.addEventListener('DOMContentLoaded', () => {
    getResources("customers").then(customers => {
        var customerList = document.createElement("ul")
        for (var customer of customers) {
            var item = document.createElement("li")
            item.innerText = customer["name"]
            customerList.appendChild(item)
        }
        document.getElementById("customer-div").appendChild(customerList);
    })
    getResources("exercises").then(exercises => {
        var exerciseList = document.createElement("ul")
        for (var exercise of exercises) {
            var item = document.createElement("li")
            item.innerText = exercise["name"]
            exerciseList.appendChild(item)
        }
        document.getElementById("exercise-div").appendChild(exerciseList);
    })
    getResources("workouts").then(workouts => {
        var workoutList = document.createElement("ul")
        for (var workout of workouts) {
            var item = document.createElement("li")
            item.innerText = workout["name"]
            workoutList.appendChild(item)
        }
        document.getElementById("workout-div").appendChild(workoutList);
    })
})
