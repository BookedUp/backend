package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.service.HostService;

import java.util.Collection;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    @Autowired
    private HostService hostService;

    /*url: /api/hosts GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Host>> getHosts() {
        Collection<Host> hosts = hostService.getAll();
        return new ResponseEntity<Collection<Host>>(hosts, HttpStatus.OK);
    }

    /* url: /api/hosts/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> getHost(@PathVariable("id") Long id) {
        Host host = hostService.getById(id);

        if (host == null) {
            return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Host>(host, HttpStatus.OK);
    }

    /*url: /api/hosts POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> createGreeting(@RequestBody Host host) throws Exception {
        Host savedHost = hostService.create(host);
        return new ResponseEntity<Host>(savedHost, HttpStatus.CREATED);
    }

    /* url: /api/hosts/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> updateGreeting(@RequestBody Host host, @PathVariable Long id)
            throws Exception {
        Host hostForUpdate = hostService.getById(id);
        hostForUpdate.copyValues(host);

        Host updatedHost = hostService.update(hostForUpdate);

        if (updatedHost == null) {
            return new ResponseEntity<Host>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Host>(updatedHost, HttpStatus.OK);
    }

    /** url: /api/hosts/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Host> deleteGreeting(@PathVariable("id") Long id) {
        hostService.delete(id);
        return new ResponseEntity<Host>(HttpStatus.NO_CONTENT);
    }
}
