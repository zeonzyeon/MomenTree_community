package com.estsoft.estsoft2ndproject.controller.api;

import com.estsoft.estsoft2ndproject.domain.dto.post.CommentResponseDTO;
import com.estsoft.estsoft2ndproject.domain.dto.user.CustomUserDetails;
import com.estsoft.estsoft2ndproject.service.MenuDataService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuDataController {

	private final MenuDataService menuDataService;

	// 알림 데이터 가져오기
	@GetMapping("/notifications")
	public List<CommentResponseDTO> getNotifications(@AuthenticationPrincipal CustomUserDetails userDetails) {
		Long userId = userDetails.getUser().getUserId();
		return menuDataService.getNotificationsWithLinks(userId);
	}


	// 작성글 데이터 가져오기
	@GetMapping("/userPosts")
	public List<CommentResponseDTO> getUserPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return menuDataService.getUserPostsWithComments(userDetails.getUser().getUserId());
	}

	// 공지 데이터 가져오기
	@GetMapping("/announcements")
	public List<CommentResponseDTO> getAnnouncements() {
		return menuDataService.getAnnouncementsWithLinks();
	}

}
