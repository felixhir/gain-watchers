const api_url = "http://localhost:8080/api/";

async function getResources(resource) {
    return fetch(api_url + resource).then(response => {
        return response.json()
    })
}

function intoNameList(items) {
    var list = document.createElement("ul")
    for (var item of items) {
        var listElement = document.createElement("li")
        listElement.innerText = item["name"]
        list.appendChild(listElement)
    }
    return list
}

document.addEventListener('DOMContentLoaded', () => {
    reloadEntities()
})

function reloadEntities() {
    getResources("customers").then(customers => {
        var customerList = intoNameList(customers)
        customerList.id = "customer-ul"
        document.getElementById("customer-ul").replaceWith(customerList)
    })
    getResources("exercises").then(exercises => {
        var exerciseList = intoNameList(exercises)
        exerciseList.id = "exercise-ul"
        document.getElementById("exercise-ul").replaceWith(exerciseList)
    })
    getResources("workouts").then(workouts => {
        var workoutList = intoNameList(workouts)
        workoutList.id = "workout-ul"
        document.getElementById("workout-ul").replaceWith(workoutList)
    })
}

function postCustomer() {
    var form = document.getElementById("customer-form")
    var formData = new FormData(form)
    var jsonData =  Object.fromEntries(formData)
    jsonData["height"] = parseInt(jsonData["height"])
    jsonData["weight"] = parseInt(jsonData["weight"])
    jsonData["bodyFatPercentage"] = parseInt(jsonData["bodyFatPercentage"])
    jsonData["daysAvailablePerWeek"] = parseInt(jsonData["daysAvailablePerWeek"])

    fetch(api_url + "customers", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData),
    }).then(response => {
        if (response.status == 201) {
            reloadEntities()
        } else {
            window.alert("You have entered an invalid value!")
        }
    })
}
