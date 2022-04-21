const { app, BrowserWindow, dialog } = require('electron')
const path = require('path')

const createWindow = () => {
    const win = new BrowserWindow({
      width: 800,
      height: 600,
      webPreferences: {
        preload: path.join(__dirname, './src/preload.js')
      }
    });
  
    win.removeMenu();
    win.loadFile('./src/index.html');
    //win.webContents.openDevTools()
}

app.whenReady().then(() => {
    createWindow();
});

try {
  require('electron-reloader')(module)
} catch (_) {
}
