package com.testdome;

public class UserInput {

    public static class TextInput {
        public void setValue(String value) {
            this.value = value;
        }

        private String value = "";

        public void add(char c) {
            StringBuilder builder = new StringBuilder(this.value);
            builder.append(c);
            this.setValue(builder.toString());
        }

        public String getValue() {
            return this.value;
        }
    }

    public static class NumericInput extends TextInput {
        @Override
        public void add(char c) {
            if (!Character.isDigit(c)) {
                return;
            }
            StringBuilder builder = new StringBuilder(super.value);
            builder.append(c);
            this.setValue(builder.toString());
        }
    }

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}