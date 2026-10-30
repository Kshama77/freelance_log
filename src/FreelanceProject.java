public class FreelanceProject {
    private String clientName;
    private String projectName;
    private double fee;
    private boolean paid;

    public FreelanceProject(String clientName, String projectName, double fee) {
        this.clientName = clientName;
        this.projectName = projectName;
        this.fee = fee;
        this.paid = false;
    }

    public String getClientName() {
        return clientName;
    }

    public String getProjectName() {
        return projectName;
    }

    public double getFee() {
        return fee;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markPaid() {
        paid = true;
    }

    public void display(int number) {
        System.out.printf("%d. Project: %s | Client: %s | Fee: $%.2f | Status: %s%n",
                number, projectName, clientName, fee, paid ? "PAID" : "UNPAID");
    }
}
