const api_url = "http://localhost:8080/api/";

var current_detail_view

async function getResources(resource) {
    return fetch(api_url + resource).then(response => {
        return response.json()
    })
}

document.addEventListener('DOMContentLoaded', () => {
    reloadEntities()

    document.getElementById("detail-div").style.visibility = "hidden"
    document.getElementById("detail-div").addEventListener("click", () => {
        document.getElementById("detail-div").style.visibility = "hidden"
        current_detail_view = ""
    })

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
    reloadCustomers()
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

function intoNameList(items) {
    var list = document.createElement("ul")
    for (var item of items) {
        var listElement = document.createElement("li")
        listElement.innerText = item["name"]
        list.appendChild(listElement)
    }
    return list
}

function reloadCustomers() {
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
                        if (response.status == 200) {
                            reloadCustomers()
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

function showDetails(customer, event) {
    var containerDiv = document.getElementById("detail-div")
    if (current_detail_view != customer["id"]) {
        if (containerDiv.firstChild != null) {
            containerDiv.removeChild(containerDiv.firstChild)
        }
    
        var customerDetails = document.createElement("div")
        customerDetails.id = "current-detail-view"
        var detailList = document.createElement("ol")
    
        var heightLi = document.createElement("li")
        heightLi.innerText = `Größe: ${customer["height"]} cm`
        var weightLi = document.createElement("li")
        weightLi.innerText = `Gewicht: ${customer["weight"]} kg`
    
        detailList.appendChild(heightLi)
        detailList.appendChild(weightLi)
        customerDetails.append(detailList)
    
        containerDiv.appendChild(customerDetails)
        containerDiv.style.visibility = "visible"
        containerDiv.style.left = (event.clientX + containerDiv.style.width).toString() + "px"
        containerDiv.style.top = (event.clientY + containerDiv.style.height).toString() + "px"

        current_detail_view = customer["id"]
    }
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
