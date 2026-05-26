package com.cleancoders.redistoolkit.keyvalue;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/kv")
@Tag(name = "Key-Value Store", description = "Basic Redis SET / GET / DELETE — the simplest Redis operations")
public class KeyValueController {

    private final KeyValueService service;

    public KeyValueController(KeyValueService service) {
        this.service = service;
    }

    @PutMapping("/{key}")
    @Operation(summary = "Store a value",
               description = "Equivalent to Redis: SET {key} {value}")
    public ResponseEntity<Void> set(@PathVariable String key,
                                    @RequestParam String value) {
        service.set(key, value);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{key}/ttl")
    @Operation(summary = "Store a value with expiry (TTL)",
               description = "Equivalent to Redis: SET {key} {value} EX {ttlSeconds} — Redis auto-deletes after TTL")
    public ResponseEntity<Void> setWithTtl(@PathVariable String key,
                                           @RequestParam String value,
                                           @RequestParam long ttlSeconds) {
        service.setWithTtl(key, value, ttlSeconds);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{key}")
    @Operation(summary = "Retrieve a value",
               description = "Equivalent to Redis: GET {key} — returns null if the key does not exist")
    public ResponseEntity<Object> get(@PathVariable String key) {
        Object value = service.get(key);
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }

    @DeleteMapping("/{key}")
    @Operation(summary = "Delete a key",
               description = "Equivalent to Redis: DEL {key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        service.delete(key);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{key}/info")
    @Operation(summary = "Check existence and TTL of a key",
               description = "Returns exists flag and remaining TTL in seconds (-1 = no expiry, -2 = missing key)")
    public ResponseEntity<Map<String, Object>> info(@PathVariable String key) {
        return ResponseEntity.ok(Map.of(
                "key", key,
                "exists", Boolean.TRUE.equals(service.exists(key)),
                "ttlSeconds", service.ttl(key)
        ));
    }

    @GetMapping
    @Operation(summary = "List all keys",
               description = "Equivalent to Redis: KEYS * — shows everything currently stored")
    public ResponseEntity<Set<String>> allKeys() {
        return ResponseEntity.ok(service.allKeys());
    }
}
