package com.github.noelyap.setterforcatan;

import com.github.noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import com.github.noelyap.setterforcatan.board.BoardRestService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/setter-for-catan")
@RequiredArgsConstructor
public class SetterForCatanController {
  final BoardRestService BoardRestService;

  @PostMapping(path = "/generate-board", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> generateBoard(@RequestBody final GenerateBoardRequest request) {
    return ResponseEntity.ok(BoardRestService.generateBoard(request));
  }
}
