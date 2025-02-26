# 🎯 IntentsNEntitiesMarkup

**IntentsNEntitiesMarkup** is a desktop application for annotating customer utterances, designed to create datasets for training models in the **Rasa** framework. The application allows you to quickly and conveniently label **intents** and **entities**, as well as export the annotations for further processing.

🔥 **Fully standalone** – all necessary libraries and dependencies are already included in the build. Just download the **ready-to-use `.exe`** and start working without additional setup!

---

## 🚀 Features

- ✅ **Text annotation**: Easily label **intents** and **entities** in utterances.
- ✅ **Standalone application**: No need to install Java, JavaFX, or any additional dependencies.
- ✅ **Data export**: Save annotations in **JSON** format for further processing.
- ✅ **Flexibility**: Edit and customize annotation labels directly within the UI.
- ✅ **Minimalist UI**: Dark blue design with simple and intuitive navigation.

![image](https://github.com/user-attachments/assets/e3a8fbef-4085-4f4d-8903-4b9abfb76406)

---

## 📥 Installation & Usage

### 🔹 1. Ready-to-use `.exe`

For a quick start, download the latest release from [Releases](https://github.com/robert-grammy/IntentsNEntitiesMarkup/releases).

1. Extract the archive.
2. Run `IntentsNEntitiesMarkup.exe`.
3. You're ready to start annotating!

### 🔹 2. Running from Source

If you prefer to build the project yourself:

```sh
git clone https://github.com/robert-grammy/IntentsNEntitiesMarkup.git
cd IntentsNEntitiesMarkup
mvn clean install
mvn javafx:run
```

> **Important!** Building requires **Java 23.0.2** and **Maven**.

---

## 🛠️ How to Use

1. Open **IntentsNEntitiesMarkup**.
2. Load your text data.
3. Annotate utterances by marking **intents** and **entities**.
4. Export the results to JSON for further processing.

> **Note:** The output files require additional processing to match the format needed for training **Rasa** models.

---

## 💡 Contributing

Want to contribute? Fork the repository, make your changes, and submit a **Pull Request**!

- 🔹 **Report an issue** → [Issues](https://github.com/robert-grammy/IntentsNEntitiesMarkup/issues)
- 📜 **License**: MIT

---

🚀 **Thank you for using IntentsNEntitiesMarkup!** If you have any suggestions or feedback, feel free to share.  

