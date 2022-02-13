const { app, BrowserWindow } = require('electron')

const createWindow = () => {
    const win = new BrowserWindow({
      width: 800,
      height: 600
    });
  
    win.loadFile('./src/index.html');
}

app.whenReady().then(() => {
    createWindow();
});

app.on("window-all-closed", () => {
    console.log("The user has terminated the program!");
    app.quit();
});
