package models;

public class ModelResponse {
  private String framework = "";
  private String expected_time = "0";
  private String command = "";
  private FormModel formModel = null;

  private static ModelResponse singModelResponse = null;

  private ModelResponse() {}

  public String getFramework() { return framework.replace("\"", ""); }

  public void setFramework(String framework) { this.framework = framework; }

  public String getexpected_time() { return expected_time; }

  public void setexpected_time(String expected_time) {
    this.expected_time = expected_time;
  }

  public String getCommand() { return command; }

  public void setCommand(String command) { this.command = command; }

  public static ModelResponse getInstance() {
    if (singModelResponse == null) {
      singModelResponse = new ModelResponse();
    }
    return singModelResponse;
  }

  public FormModel getFormModel() { return formModel; }

  public void setFormModel(FormModel formModel) { this.formModel = formModel; }
}
