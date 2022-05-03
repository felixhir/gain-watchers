const api_url = "http://localhost:8080/api/";

var old_name

async function getResources(resource) {
    return fetch(api_url + resource).then(response => {
        return response.json()
    })
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
})

function intoNameList(items) {
    var list = document.createElement("ul")
    for (var item of items) {
        var listElement = document.createElement("li")
        listElement.innerText = item["name"]
        list.appendChild(listElement)
    }
    return list
}

function reloadEntities() {
    loadCustomers()
    loadWorkouts()
    getResources("exercises").then(exercises => {
        var exerciseList = intoNameList(exercises)
        exerciseList.id = "exercise-ul"
        document.getElementById("exercise-ul").replaceWith(exerciseList)
    })
}

function loadWorkouts() {
    getResources("workouts").then(workouts => {
        var list = document.createElement("ul")

        for (let workout of workouts) {
            var workoutElement = document.createElement("div")
            workoutElement.classList = "workout"

            var workoutName = document.createElement("h3")
            workoutName.innerText = `${workout["name"]} (${workout["days"]} d/w)`
            var workoutDescription = document.createElement("p")
            if (workout["description"] != "") {
                workoutDescription.innerText = workout["description"]
            } else {
                workoutDescription.innerText = "No description provided."
            }

            var editButton = document.createElement("button")
            editButton.innerHTML = "Edit"
            editButton.addEventListener("click", () => {
                openEditView(workout)
            })

            workoutElement.appendChild(workoutName)
            workoutElement.appendChild(workoutDescription)
            workoutElement.appendChild(editButton)

            list.appendChild(workoutElement)
        }

        list.id = "workout-ul"
        document.getElementById("workout-ul").replaceWith(list)
    })
}

function openEditView(workout) {
    var form = document.getElementById("edit-form")

    var nameInput = document.getElementById("edit-name")
    nameInput.value = workout["name"]
    old_name = workout["name"]
    nameInput.removeAttribute("disabled")

    var descriptionInput = document.getElementById("edit-description")
    descriptionInput.value = workout["description"]
    descriptionInput.removeAttribute("disabled")

    var dayInput = document.getElementById("edit-days")
    dayInput.value = workout["days"]
    dayInput.removeAttribute("disabled")

    var newList = document.createElement("ol")
    newList.id = "edit-ol"
    document.getElementById("edit-ol").replaceWith(newList)
    for (let exercise of workout["exercises"]) {
        createExerciseEntry(exercise, "edit-ol")
    }

    document.getElementById("edit-add").removeAttribute("disabled")
    document.getElementById("edit-remove").removeAttribute("disabled")
    document.getElementById("edit-submit").removeAttribute("disabled")
} 

function loadCustomers() {
    getResources("customers").then(customers => {
        getResources("workouts").then(workouts => {
            var list = document.createElement("ul")
            var id_count = 0

            for (let customer of customers) {
                var customerElement = document.createElement("div")
                customerElement.classList = "customer"
                var detailList = document.createElement("ul")
                detailList.classList = "customer-list"
                
                var customerName = document.createElement("h3")
                customerName.innerText = customer["name"]
                var customerBaseStats = document.createElement("li")
                customerBaseStats.innerText = `${customer["height"]}cm at ${customer["weight"]}kg`
                var customerFat = document.createElement("li")
                customerFat.innerText = `Body Fat: ${customer["bodyFatPercentage"]}%`
                var customerAvailability = document.createElement("li")
                customerAvailability.innerText = `${customer["availability"]} days / week`

                customerElement.appendChild(customerName)
                detailList.appendChild(customerBaseStats)
                detailList.appendChild(customerFat)
                detailList.appendChild(customerAvailability)
    
                var customerWorkouts = document.createElement("li")
                var workoutString = "Assigned Workouts:"
                for (let workout of customer["workouts"]) {
                    workoutString = `${workoutString} "${workout}",`
                }
                if (customer["workouts"].length == 0) {
                    workoutString = "Assigned Workouts: None "
                }
                customerWorkouts.innerText = workoutString.slice(0, -1)
                detailList.appendChild(customerWorkouts)
    
                var assignWorkoutField = document.createElement("div")
                assignWorkoutField.classList = "workout-assignment"
                var workoutSelector = document.createElement("select")
                workoutSelector.id = `workout-select-${id_count}`
                for (let workout of workouts) {
                    var option = document.createElement("option")
                    option.innerHTML = workout["name"]
                    workoutSelector.appendChild(option)
                }
                var assignButton = document.createElement("button")
                assignButton.innerHTML = "Assign"
                assignButton.id = `workout-assign-${id_count++}`
                assignButton.addEventListener("click", (event) => {
                    var id = event.target.id.split("workout-assign-")[1]
                    var workout = document.getElementById(`workout-select-${id}`).value
                    fetch(`${api_url}customers/${customer["id"]}?workoutName=${workout}`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        }
                    }).then(response => {
                        if (response.status == 400) {
                            response.text().then(errorMessage => {
                                displayError(errorMessage)
                            })
                        } else if(response.status == 200) {
                            loadCustomers()
                        } else {
                            displayError("Internal Server Error")
                        }
                    })
                })

                assignWorkoutField.appendChild(workoutSelector)
                assignWorkoutField.appendChild(assignButton)
                customerElement.appendChild(assignWorkoutField)
    
                //////////////
                customerElement.appendChild(detailList)
                var customerItem = document.createElement("li")
                customerItem.appendChild(customerElement)
    
                list.appendChild(customerItem)
            }
            list.id = "customer-ul"
            document.getElementById("customer-ul").replaceWith(list)
        })
    })
}

function createExerciseEntry(data, listId) {
    const entryList = document.getElementById(listId)
    var entry = document.createElement("li")
    var exerciseSelection = document.createElement("select")

    fetch(api_url + "exercises").then(response => {
        response.json().then(exercises => {
            for (var exercise of exercises) {
                var option = document.createElement("option")
                option.textContent = `${exercise["name"]} (${exercise["exerciseVariant"]})`
                option.value = `${exercise["name"]} (${exercise["exerciseVariant"]})`
                exerciseSelection.appendChild(option)
            }
            var setInput = document.createElement("input")
            setInput.placeholder = "Sets"
            setInput.type = "number"
            var repInput = document.createElement("input")
            repInput.placeholder = "Reps"
            repInput.type = "number"

            if (data != null) {
                for (var option of exerciseSelection.options) {
                    var name = `${data["exerciseName"]} (${data["exerciseVariant"]})`
                    if (option.textContent == name) {
                        exerciseSelection.value = name
                    }
                }
                setInput.value = data["sets"]
                repInput.value = data["reps"]
            }

            entry.appendChild(exerciseSelection)
            entry.appendChild(setInput)
            entry.appendChild(repInput)
            entryList.appendChild(entry)
        })
    })
}

function removeLastExercise(listId) {
    var entryList = document.getElementById(listId)
    if (entryList.childNodes.length > 0) {
        entryList.removeChild(entryList.lastChild)
    }
}

function postCustomer() {
    var form = document.getElementById("customer-form")
    var formData = new FormData(form)
    var jsonData =  Object.fromEntries(formData)
    jsonData["height"] = parseInt(jsonData["height"])
    jsonData["weight"] = parseInt(jsonData["weight"])
    jsonData["bodyFatPercentage"] = parseInt(jsonData["bodyFatPercentage"])
    jsonData["availability"] = parseInt(jsonData["availability"])

    fetch(api_url + "customers", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData),
    }).then(response => {
        if (response.status == 400) {
            response.text().then(errorMessage => {
                displayError(errorMessage)
            })
        } else if(response.status == 201) {
            reloadEntities()
        } else {
            displayError("Internal Server Error")
        }
    })
}

function postExercise() {
    var form = document.getElementById("exercise-form")
    var formData = new FormData(form)
    var jsonData = Object.fromEntries(formData)
    
    fetch(api_url + "exercises", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData)
    }).then(response => {
        if (response.status == 400) {
            response.text().then(errorMessage => {
                displayError(errorMessage)
            })
        } else if(response.status == 201) {
            reloadEntities()
        } else {
            displayError("Internal Server Error")
        }
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

    fetch(api_url + "workouts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData)
    }).then(response => {
        if (response.status == 400) {
            response.text().then(errorMessage => {
                displayError(errorMessage)
            })
        } else if(response.status == 201) {
            reloadEntities()
        } else {
            displayError("Internal Server Error")
        }
    })
}

function updateWorkout() {
    const exerciseList = document.getElementById("edit-ol")
    const form = document.getElementById("update-form")
    var formData = new FormData(form)
    var jsonData = Object.fromEntries(formData)

    var exerciseArray = []
    for (var child of exerciseList.children) {
        var exercise = child.children[0].value
        var sets = child.children[1].value
        var reps = child.children[2].value
        
        exerciseArray.push({
            "exerciseName": exercise.split(" (")[0],
            "exerciseVariant": exercise.split(" (")[1].toUpperCase().replace(" ", "_").slice(0, -1),
            "sets": sets,
            "reps": reps
        })
    }
    jsonData["exercises"] = exerciseArray

    fetch(`${api_url}workouts/${old_name}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData)
    }).then(response => {
        if (response.status == 400) {
            response.text().then(errorMessage => {
                displayError(errorMessage)
            })
        } else {
            reloadEntities()
        } 
    })
}

function displayError(message) {
    window.alert(`There has been an error with your request. Reason: ${message}`)
}
