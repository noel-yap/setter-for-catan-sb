package noelyap.setterforcatan;

import lombok.RequiredArgsConstructor;
import noelyap.setterforcatan.board.BoardRestService;
import noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setter-for-catan")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SetterForCatanController {
  final BoardRestService BoardRestService;

  @PostMapping(path = "/generate-board", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> generateBoard(@RequestBody final GenerateBoardRequest request) {
    return ResponseEntity.ok(BoardRestService.generateBoard(request));
  }
}
