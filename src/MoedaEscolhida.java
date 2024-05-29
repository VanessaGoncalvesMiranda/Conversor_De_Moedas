public record MoedaEscolhida(String base_code, String target_code, double conversion_rate) {

        // Método público para acessar o campo conversion_rate
    public double getConversionRate() {
        return this.conversion_rate;
    }
}