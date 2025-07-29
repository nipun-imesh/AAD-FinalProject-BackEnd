//package lk.ijse.gdse.wanderlust.servies.impl;
//
//import jakarta.annotation.PostConstruct;
//import lk.ijse.gdse.wanderlust.entity.TicketData;
//import lk.ijse.gdse.wanderlust.repo.TicketPreviewRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//@Service
//public class TicketDataGenerator {
//    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//
//    private final TicketPreviewRepository repository;
//    private final Random random = new Random();
//
//    private final String[] airlines = {
//            "SriLankan Airlines", "Emirates", "Qatar Airways",
//            "Singapore Airlines", "Turkish Airlines", "Etihad Airways",
//            "Cathay Pacific", "Japan Airlines", "Air India", "Thai Airways",
//            "British Airways", "Air France", "Lufthansa", "KLM", "American Airlines",
//            "Delta Airlines", "United Airlines", "Qantas", "Swiss International Air Lines"
//    };
//
//    private final String[] airports = {
//            "Colombo (CMB)", "Dubai (DXB)", "Doha (DOH)", "London (LHR)", "Singapore (SIN)", "Istanbul (IST)",
//            "Bangkok (BKK)", "Kuala Lumpur (KUL)", "Paris (CDG)", "New York (JFK)", "Tokyo (HND)", "Sydney (SYD)",
//            "Mumbai (BOM)", "Delhi (DEL)", "Frankfurt (FRA)", "Toronto (YYZ)", "Los Angeles (LAX)", "Chicago (ORD)",
//            "Hong Kong (HKG)", "Seoul (ICN)", "Madrid (MAD)", "Barcelona (BCN)", "San Francisco (SFO)",
//            "Rome (FCO)", "Amsterdam (AMS)", "Zurich (ZRH)", "Vienna (VIE)", "Copenhagen (CPH)", "Stockholm (ARN)",
//            "Osaka (KIX)", "Melbourne (MEL)", "Auckland (AKL)", "Cape Town (CPT)", "Johannesburg (JNB)",
//            "Brussels (BRU)", "Lisbon (LIS)", "Athens (ATH)", "Warsaw (WAW)", "Helsinki (HEL)", "Oslo (OSL)",
//            "Budapest (BUD)", "Prague (PRG)", "Moscow (SVO)", "Shanghai (PVG)", "Beijing (PEK)", "Manila (MNL)",
//            "Jakarta (CGK)", "Hanoi (HAN)", "Ho Chi Minh City (SGN)", "Phuket (HKT)", "Male (MLE)", "Muscat (MCT)",
//            "Riyadh (RUH)", "Jeddah (JED)", "Abu Dhabi (AUH)", "Bahrain (BAH)", "Amman (AMM)", "Casablanca (CMN)",
//            "Mexico City (MEX)", "Bogota (BOG)", "Lima (LIM)", "Santiago (SCL)", "Buenos Aires (EZE)",
//            "Vancouver (YVR)", "Montreal (YUL)", "Boston (BOS)", "Miami (MIA)", "Dallas (DFW)",
//            "Houston (IAH)", "Atlanta (ATL)", "Seattle (SEA)", "Philadelphia (PHL)", "Washington (IAD)", "Orlando (MCO)",
//            "Denver (DEN)", "Las Vegas (LAS)", "Phoenix (PHX)", "Detroit (DTW)", "Minneapolis (MSP)", "San Diego (SAN)",
//            "Charlotte (CLT)", "Tampa (TPA)", "Portland (PDX)", "Austin (AUS)", "Newark (EWR)", "San Jose (SJC)"
//    };
//
//    private final String[] classes = {"Economy", "Business", "First"};
//    private final String[] ticketTypes = {"One-way", "Round-trip"};
//    private final String[] cancelPolicies = {"Non-refundable", "Refundable with fee", "Fully refundable"};
//
//    public TicketDataGenerator(TicketPreviewRepository repository) {
//        this.repository = repository;
//    }
//
//    @PostConstruct
//    public void init() {
//        generateTickets(20000);  // Generate 20,000 tickets
//    }
//
//    public void generateTickets(int count) {
//        List<TicketData> tickets = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            String airline = airlines[random.nextInt(airlines.length)];
//            String departureAirport = airports[random.nextInt(airports.length)];
//            String arrivalAirport;
//            do {
//                arrivalAirport = airports[random.nextInt(airports.length)];
//            } while (arrivalAirport.equals(departureAirport));
//
//            String flightNumber = airline.substring(0, 2).toUpperCase() + (100 + random.nextInt(900));
//            String depTerminal = "T" + (1 + random.nextInt(3));
//            String arrTerminal = "T" + (1 + random.nextInt(3));
//
//            LocalDateTime depTime = LocalDateTime.now().plusDays(random.nextInt(60)).withHour(random.nextInt(24)).withMinute(random.nextInt(60)).withSecond(0).withNano(0);
//            LocalDateTime arrTime = depTime.plusHours(3 + random.nextInt(9));
//            String duration = (arrTime.getHour() - depTime.getHour()) + "h " + Math.abs(arrTime.getMinute() - depTime.getMinute()) + "m";
//
//            int totalPrice = 100 + random.nextInt(1300);  // Fixed whole number
//
//            tickets.add(TicketData.builder()
//                    .airlineName(airline)
//                    .flightNumber(flightNumber)
//                    .departureAirport(departureAirport)
//                    .arrivalAirport(arrivalAirport)
//                    .departureTerminal(depTerminal)
//                    .arrivalTerminal(arrTerminal)
//                    .departureDate(depTime.format(dateFormatter))
//                    .departureTime(depTime.format(timeFormatter))
//                    .arrivalDate(arrTime.format(dateFormatter))
//                    .arrivalTime(arrTime.format(timeFormatter))
//                    .flightDuration(duration)
//                    .totalPrice(Double.valueOf(totalPrice))
//                    .currency("USD")
//                    .refundable(random.nextBoolean())
//                    .cabinBaggageKg((double) (7 + random.nextInt(5)))
//                    .checkedBaggageKg((double) (20 + random.nextInt(20)))
//                    .mealIncluded(random.nextBoolean())
//                    .seatSelectionAvailable(random.nextBoolean())
//                    .travelClass(classes[random.nextInt(classes.length)])
//                    .ticketType(ticketTypes[random.nextInt(ticketTypes.length)])
//                    .changePolicy("Changes allowed with $" + (30 + random.nextInt(120)) + " fee")
//                    .cancellationPolicy(cancelPolicies[random.nextInt(cancelPolicies.length)])
//                    .build());
//        }
//        repository.saveAll(tickets);
//    }
//
//}
