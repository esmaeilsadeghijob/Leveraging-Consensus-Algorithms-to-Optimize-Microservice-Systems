package com.example.paxosconsensusservice;

@RestController
@RequestMapping("/paxos")
public class PaxosController {

    private final PaxosService paxosService;

    @Autowired
    public PaxosController(PaxosService paxosService) {
        this.paxosService = paxosService;
    }

    @PostMapping("/propose")
    public ResponseEntity<String> propose(@RequestParam int proposalId, @RequestParam int value) {
        if (paxosService.propose(proposalId, value)) {
            return ResponseEntity.ok("Proposal accepted");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Proposal rejected");
    }

    @PostMapping("/accept")
    public ResponseEntity<String> accept(@RequestParam int proposalId, @RequestParam int value) {
        if (paxosService.accept(proposalId, value)) {
            return ResponseEntity.ok("Value accepted");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Value rejected");
    }

    @GetMapping("/value")
    public ResponseEntity<Integer> getValue(@RequestParam int proposalId) {
        Integer acceptedValue = paxosService.getAcceptedValue(proposalId);
        if (acceptedValue != null) {
            return ResponseEntity.ok(acceptedValue);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

