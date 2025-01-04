package com.example.paxosconsensusservice;

@RestController
@RequestMapping("/advanced-paxos")
public class AdvancedPaxosController {

    private final AdvancedPaxosService paxosService;

    @Autowired
    public AdvancedPaxosController(AdvancedPaxosService paxosService) {
        this.paxosService = paxosService;
    }

    @PostMapping("/propose")
    public ResponseEntity<String> propose(@RequestParam int proposalId, @RequestParam int value) {
        if (paxosService.propose(proposalId, value)) {
            return ResponseEntity.ok("Proposal accepted");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Proposal rejected");
    }

    @PostMapping("/promise")
    public ResponseEntity<String> promise(@RequestParam int proposalId, @RequestParam int value) {
        if (paxosService.promise(proposalId, value)) {
            return ResponseEntity.ok("Promise accepted");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Promise rejected");
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
