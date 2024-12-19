package vn.edu.iuh.fit.enums;

public enum TrangThai {
    CHUA_CHAP_NHAN(0), // 0
    CHAP_NHAN(1);      // 1

    private final int value;

    TrangThai(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrangThai fromValue(int value) {
        for (TrangThai tt : TrangThai.values()) {
            if (tt.value == value) {
                return tt;
            }
        }
        throw new IllegalArgumentException("Invalid TrangThai value: " + value);
    }
}
