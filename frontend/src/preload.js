const axios = require('axios')

const api_url = "http://localhost:8080/api/";
/*
function getResources(resource) {
  return new Promise(function(resolve, reject) {
    get(api_url + resource)
  });
}*/

document.getElementById("button").addEventListener('click', () => {
  window.alert("aaaaaaa");
   /* getResources("customers").then(customers => {
        var customerString = "";
        for (var customer of JSON.parse(customers)) {
          customerString = `${customerString}\n<td>${customer.name}</td>`;
        }
        window.alert(customerString);
        document.getElementById("customers").innerHTML = "test";
      });*/
});

window.addEventListener('DOMContentLoaded', () => {
    const replaceText = (selector, text) => {
      const element = document.getElementById(selector)
      if (element) element.innerText = text
    }
  
    for (const dependency of ['chrome', 'node', 'electron']) {
      replaceText(`${dependency}-version`, process.versions[dependency])
    }
});