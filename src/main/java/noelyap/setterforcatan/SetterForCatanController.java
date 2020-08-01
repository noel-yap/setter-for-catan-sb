package noelyap.setterforcatan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noelyap.setterforcatan.board.BoardRestService;
import noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import noelyap.setterforcatan.board.protogen.GenerateBoardResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setter-for-catan")
@CrossOrigin(
    origins = {"http://localhost:3001", "http://setter-for-catan.com:3001"},
    maxAge = 3600) // TODO(nyap): Use SSL.
@RequiredArgsConstructor
@Slf4j
public class SetterForCatanController {
  private final BoardRestService boardRestService;

  @PostMapping(
      path = "/generate-board",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> generateBoard(@RequestBody final GenerateBoardRequest request) {
    log.info(String.format("request = `%s`", request.toString()));

    final ResponseEntity<GenerateBoardResponse> responseEntity =
        ResponseEntity.ok(boardRestService.generateBoard(request));

    return responseEntity;
  }
}
