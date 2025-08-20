//package lk.ijse.gdse.wanderlust.servies.impl;
//
//import jakarta.annotation.PostConstruct;
//import lk.ijse.gdse.wanderlust.entity.TicketData;
//import lk.ijse.gdse.wanderlust.repo.TicketPreviewRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//@Service
//public class TicketDataGenerator {
//    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
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
//    // Airport → Country mapping
//    private final Map<String, String> airportCountryMap = Map.ofEntries(
//            Map.entry("Colombo (CMB)", "Sri Lanka"),
//            Map.entry("Dubai (DXB)", "United Arab Emirates"),
//            Map.entry("Doha (DOH)", "Qatar"),
//            Map.entry("London (LHR)", "United Kingdom"),
//            Map.entry("Singapore (SIN)", "Singapore"),
//            Map.entry("Istanbul (IST)", "Türkiye"),
//            Map.entry("Bangkok (BKK)", "Thailand"),
//            Map.entry("Kuala Lumpur (KUL)", "Malaysia"),
//            Map.entry("Paris (CDG)", "France"),
//            Map.entry("New York (JFK)", "USA"),
//            Map.entry("Tokyo (HND)", "Japan"),
//            Map.entry("Sydney (SYD)", "Australia"),
//            Map.entry("Mumbai (BOM)", "India"),
//            Map.entry("Delhi (DEL)", "India"),
//            Map.entry("Frankfurt (FRA)", "Germany"),
//            Map.entry("Toronto (YYZ)", "Canada"),
//            Map.entry("Los Angeles (LAX)", "USA"),
//            Map.entry("Chicago (ORD)", "USA"),
//            Map.entry("Hong Kong (HKG)", "Hong Kong SAR"),
//            Map.entry("Seoul (ICN)", "South Korea"),
//            Map.entry("Madrid (MAD)", "Spain"),
//            Map.entry("Barcelona (BCN)", "Spain"),
//            Map.entry("San Francisco (SFO)", "USA"),
//            Map.entry("Rome (FCO)", "Italy"),
//            Map.entry("Amsterdam (AMS)", "Netherlands"),
//            Map.entry("Zurich (ZRH)", "Switzerland"),
//            Map.entry("Vienna (VIE)", "Austria"),
//            Map.entry("Copenhagen (CPH)", "Denmark"),
//            Map.entry("Stockholm (ARN)", "Sweden"),
//            Map.entry("Osaka (KIX)", "Japan"),
//            Map.entry("Melbourne (MEL)", "Australia"),
//            Map.entry("Auckland (AKL)", "New Zealand"),
//            Map.entry("Cape Town (CPT)", "South Africa"),
//            Map.entry("Johannesburg (JNB)", "South Africa"),
//            Map.entry("Brussels (BRU)", "Belgium"),
//            Map.entry("Lisbon (LIS)", "Portugal"),
//            Map.entry("Athens (ATH)", "Greece"),
//            Map.entry("Warsaw (WAW)", "Poland"),
//            Map.entry("Helsinki (HEL)", "Finland"),
//            Map.entry("Oslo (OSL)", "Norway"),
//            Map.entry("Budapest (BUD)", "Hungary"),
//            Map.entry("Prague (PRG)", "Czech Republic"),
//            Map.entry("Moscow (SVO)", "Russia"),
//            Map.entry("Shanghai (PVG)", "China"),
//            Map.entry("Beijing (PEK)", "China"),
//            Map.entry("Manila (MNL)", "Philippines"),
//            Map.entry("Jakarta (CGK)", "Indonesia"),
//            Map.entry("Hanoi (HAN)", "Vietnam"),
//            Map.entry("Ho Chi Minh City (SGN)", "Vietnam"),
//            Map.entry("Phuket (HKT)", "Thailand"),
//            Map.entry("Male (MLE)", "Maldives"),
//            Map.entry("Muscat (MCT)", "Oman"),
//            Map.entry("Riyadh (RUH)", "Saudi Arabia"),
//            Map.entry("Jeddah (JED)", "Saudi Arabia"),
//            Map.entry("Abu Dhabi (AUH)", "United Arab Emirates"),
//            Map.entry("Bahrain (BAH)", "Bahrain"),
//            Map.entry("Amman (AMM)", "Jordan"),
//            Map.entry("Casablanca (CMN)", "Morocco"),
//            Map.entry("Mexico City (MEX)", "Mexico"),
//            Map.entry("Bogota (BOG)", "Colombia"),
//            Map.entry("Lima (LIM)", "Peru"),
//            Map.entry("Santiago (SCL)", "Chile"),
//            Map.entry("Buenos Aires (EZE)", "Argentina"),
//            Map.entry("Vancouver (YVR)", "Canada"),
//            Map.entry("Montreal (YUL)", "Canada"),
//            Map.entry("Boston (BOS)", "USA"),
//            Map.entry("Miami (MIA)", "USA"),
//            Map.entry("Dallas (DFW)", "USA"),
//            Map.entry("Houston (IAH)", "USA"),
//            Map.entry("Atlanta (ATL)", "USA"),
//            Map.entry("Seattle (SEA)", "USA"),
//            Map.entry("Philadelphia (PHL)", "USA"),
//            Map.entry("Washington (IAD)", "USA"),
//            Map.entry("Orlando (MCO)", "USA"),
//            Map.entry("Denver (DEN)", "USA"),
//            Map.entry("Las Vegas (LAS)", "USA"),
//            Map.entry("Phoenix (PHX)", "USA"),
//            Map.entry("Detroit (DTW)", "USA"),
//            Map.entry("Minneapolis (MSP)", "USA"),
//            Map.entry("San Diego (SAN)", "USA"),
//            Map.entry("Charlotte (CLT)", "USA"),
//            Map.entry("Tampa (TPA)", "USA"),
//            Map.entry("Portland (PDX)", "USA"),
//            Map.entry("Austin (AUS)", "USA"),
//            Map.entry("Newark (EWR)", "USA"),
//            Map.entry("San Jose (SJC)", "USA")
//    );
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
//        generateTickets(20000);
//    }
//
//    public void generateTickets(int count) {
//        List<TicketData> tickets = new ArrayList<>();
//        List<String> airports = new ArrayList<>(airportCountryMap.keySet());
//
//        for (int i = 0; i < count; i++) {
//            String airline = airlines[random.nextInt(airlines.length)];
//            String departureAirport = airports.get(random.nextInt(airports.size()));
//            String arrivalAirport;
//            do {
//                arrivalAirport = airports.get(random.nextInt(airports.size()));
//            } while (arrivalAirport.equals(departureAirport));
//
//            String flightNumber = airline.substring(0, 2).toUpperCase() + (100 + random.nextInt(900));
//            String depTerminal = "T" + (1 + random.nextInt(3));
//            String arrTerminal = "T" + (1 + random.nextInt(3));
//
//            LocalDateTime depTime = LocalDateTime.now()
//                    .plusDays(random.nextInt(60))
//                    .withHour(random.nextInt(24))
//                    .withMinute(random.nextInt(60))
//                    .withSecond(0)
//                    .withNano(0);
//            LocalDateTime arrTime = depTime.plusHours(3 + random.nextInt(9));
//            String duration = (arrTime.getHour() - depTime.getHour()) + "h " +
//                    Math.abs(arrTime.getMinute() - depTime.getMinute()) + "m";
//
//            int totalPrice = 100 + random.nextInt(1300);
//
//            tickets.add(TicketData.builder()
//                    .airlineName(airline)
//                    .flightNumber(flightNumber)
//                    .country(airportCountryMap.get(departureAirport)) // map country
//                    .departureAirport(departureAirport)
//                    .arrivalAirport(arrivalAirport)
//                    .departureTerminal(depTerminal)
//                    .arrivalTerminal(arrTerminal)
//                    .departureDate(depTime.format(dateFormatter))
//                    .departureTime(depTime.format(timeFormatter))
//                    .arrivalDate(arrTime.format(dateFormatter))
//                    .arrivalTime(arrTime.format(timeFormatter))
//                    .flightDuration(duration)
//                    .totalPrice((double) totalPrice)
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
//}
