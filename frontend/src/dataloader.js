document.addEventListener('DomContentLoaded', () => {
    window.alert('Loaded');
});


trainees = [
    {
        "name": "Max Mustermann",
        "availability": 5
    },
    {
        "name": "John Doe",
        "availability": 3
    }
];
const names = "";
const availabilities = "";
for (trainee in trainees) {
    names.concat(`<td>${trainee.name}</td>\n`);
    availabilities.concat(`<td>${trainee.availability}</td>`);
}
replaceText("trainees", names);

window.alert("test successful");