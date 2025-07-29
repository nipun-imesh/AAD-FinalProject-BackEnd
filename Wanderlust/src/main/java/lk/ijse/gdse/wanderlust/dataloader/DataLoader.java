//package lk.ijse.gdse.wanderlust.dataloader;
//
//import lk.ijse.gdse.wanderlust.servies.impl.TicketDataGenerator;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final TicketDataGenerator generator;
//
//    public DataLoader(TicketDataGenerator generator) {
//        this.generator = generator;
//    }
//
//    @Override
//    public void run(String... args) {
//        generator.generateTickets(3000); // Generates 3000 random tickets at startup
//        System.out.println("3000 Tickets Generated!");
//    }
//}
