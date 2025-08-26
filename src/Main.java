public class Main {
    public static void main(String[] args) {
      try {
        MenuCLI menu = new MenuCLI();
        menu.iniciar();
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
}
