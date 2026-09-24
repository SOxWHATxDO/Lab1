package com.example.lab1;

/** Pure Java; small teaching ranges are a product requirement, not an Android limit. */
public final class InputRules {
    private InputRules() { }

    public static int positive(String raw, int maximum) {
        if (maximum < 1) throw new IllegalArgumentException("Некорректный максимум");
        if (raw == null || raw.trim().isEmpty()) {
            throw new IllegalArgumentException("Заполните поле");
        }
        final int value;
        try {
            value = Integer.parseInt(raw.trim());
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Введите целое число", error);
        }
        if (value < 1 || value > maximum) {
            throw new IllegalArgumentException("Допустимо от 1 до " + maximum);
        }
        return value;
    }

    public static int fullDays(int inventory, int dayUse) {
        if (dayUse < 1) {
            throw new IllegalArgumentException("Расход должен быть положительным");
        }
        return inventory / dayUse;
    }

    public static int remainder(int inventory, int dayUse) {
        if (dayUse < 1) {
            throw new IllegalArgumentException("Расход должен быть положительным");
        }
        return inventory % dayUse;
    }
}
