package enums;

public enum AppointmentStatus {
    NEW("новый"),
    IN_PROCESS("в процессе"),
    CANCELLED("отменен"),
    AWAITING_PAYMENT("ожидает оплаты"),
    COMPLETED("завершен");

    private final String text;

    AppointmentStatus(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
