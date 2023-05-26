package models;

public class ModelResponse {
    private String framework = "";
    private String command = "";
    private FormModel formModel = null;

    private static ModelResponse singModelResponse = null;

    private ModelResponse() {

    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public static ModelResponse getInstance() {
        if (singModelResponse == null) {
            singModelResponse = new ModelResponse();
        }
        return singModelResponse;
    }

    public FormModel getFormModel() {
        return formModel;
    }

    public void setFormModel(FormModel formModel) {
        this.formModel = formModel;
    }
}
