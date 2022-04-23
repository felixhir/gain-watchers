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
    
    fetch(api_url + "exercises/variants").then(response => {
        response.json().then(variants => {
            const variantSelection = document.getElementById("variant-selection")
            for (var variant of variants) {
                var item = document.createElement("option")
                item.textContent = variant
                variantSelection.appendChild(item)
            }
        })
    })
    fetch(api_url + "exercises/types").then(response => {
        response.json().then(types => {
            const typeSelection = document.getElementById("type-selection")
            for (var type of types) {
                var item = document.createElement("option")
                item.textContent = type
                typeSelection.appendChild(item)
            }
        })
    })
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

async function postExercise() {
    var form = document.getElementById("exercise-form")
    var formData = new FormData(form)
    var jsonData = Object.fromEntries(formData)
    
    await fetch(api_url + "exercises", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData)
    }).then(response => {
        if (response.status == 201) {
            reloadEntities()
        }
    })
}

function createExerciseEntry() {
    const entryList = document.getElementById("workout-exercise-ol")
    var entry = document.createElement("li")
    var exerciseSelection = document.createElement("select")

    fetch(api_url + "exercises").then(response => {
        response.json().then(exercises => {
            for (var exercise of exercises) {
                var option = document.createElement("option")
                option.textContent = `${exercise["name"]} (${exercise["exerciseVariant"]})`
                exerciseSelection.appendChild(option)
            }
            entry.appendChild(exerciseSelection)
            var setInput = document.createElement("input")
            setInput.placeholder = "Sets"
            setInput.type = "number"
            entry.appendChild(setInput)
            var repInput = document.createElement("input")
            repInput.placeholder = "Reps"
            repInput.type = "number"
            entry.appendChild(repInput)

            entryList.appendChild(entry)
        })
    })
}

function postWorkout() {
    const exerciseList = document.getElementById("workout-exercise-ol")
    const form = document.getElementById("workout-form")
    var formData = new FormData(form)
    var jsonData = Object.fromEntries(formData)

    var exerciseArray = []
    for (var child of exerciseList.children) {
        var exercise = child.children[0].value
        var sets = child.children[1].value
        var reps = child.children[2].value

        exerciseArray.push({
            "exerciseName": exercise.split(" (")[0],
            "exerciseVariant": exercise.split(" (")[1].toUpperCase().slice(0, -1),
            "sets": sets,
            "reps": reps
        })
    }
    jsonData["exercises"] = exerciseArray
    console.log(jsonData)

    fetch(api_url + "workouts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData)
    }).then(response => {
        if (response.status == 201) {
            reloadEntities()
        }
    })
}
