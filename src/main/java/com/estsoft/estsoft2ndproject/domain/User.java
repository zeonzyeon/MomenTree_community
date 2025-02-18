package com.estsoft.estsoft2ndproject.domain;

import org.springframework.data.annotation.CreatedDate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
	private String email;

	@Column(name = "nickname", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
	private String nickname;

	@Column(name = "pii", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
	private String pii;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private Timestamp createdAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private Timestamp updatedAt;

	@Column(name = "is_active", columnDefinition = "TINYINT(1)", nullable = false)
	private Boolean isActive;

	@Column(name = "level", columnDefinition = "VARCHAR(255)", nullable = false)
	private String level;

	@Column(name = "last_login", nullable = false)
	private Timestamp lastLogin;

	@Column(name = "login_count", nullable = false)
	private Integer loginCount;

	@Column(name = "user_agent", columnDefinition = "TEXT", nullable = false)
	private String userAgent;

	@Column(name = "profile_image_url", columnDefinition = "TEXT", nullable = true)
	private String profileImageUrl;

	@Column(name = "activity_score", nullable = false)
	private Integer activityScore;

	@Column(name = "badge_image_data", columnDefinition = "TEXT", nullable = true)
	private String badgeImageData;

	@Column(name = "awarded_title", columnDefinition = "VARCHAR(255)", nullable = true)
	private String awardedTitle;

	@Column(name = "self_intro", columnDefinition = "TEXT", nullable = true)
	private String selfIntro;

	@Column(name = "sns_link", columnDefinition = "TEXT", nullable = true)
	private String snsLink;

	@Builder
	public User(String email, String nickname, String pii, Boolean isActive, String level, Timestamp lastLogin, Integer loginCount, String userAgent, String profileImageUrl, Integer activityScore,
		String badgeImageData, String awardedTitle, String selfIntro, String snsLink) {
		this.email = email;
		this.nickname = nickname;
		this.pii = pii;
		this.isActive = isActive != null ? isActive : true;
		this.level = level;
		this.lastLogin = lastLogin != null ? lastLogin : new Timestamp(System.currentTimeMillis());
		this.loginCount = loginCount != null ? loginCount : 0;
		this.userAgent = userAgent;
		this.profileImageUrl = profileImageUrl;
		this.activityScore = activityScore != null ? activityScore : 0;
		this.badgeImageData = badgeImageData;
		this.awardedTitle = awardedTitle;
		this.selfIntro = selfIntro;
		this.snsLink = snsLink;
	}

	@Builder(builderMethodName = "updateBuilder")
	public void updateUser(String nickname, Boolean isActive, String level, Timestamp lastLogin, Integer loginCount, String userAgent, String profileImageUrl, Integer activityScore,
		String badgeImageData, String awardedTitle, String selfIntro, String snsLink) {
		this.nickname = nickname != null ? nickname : this.nickname;
		this.isActive = isActive != null ? isActive : this.isActive;
		this.level = level != null ? level : this.level;
		this.lastLogin = lastLogin != null ? lastLogin : this.lastLogin;
		this.loginCount = loginCount != null ? loginCount : this.loginCount;
		this.userAgent = userAgent != null ? userAgent : this.userAgent;
		this.profileImageUrl = profileImageUrl != null ? profileImageUrl : this.profileImageUrl;
		this.activityScore = activityScore != null ? activityScore : this.activityScore;
		this.badgeImageData = badgeImageData != null ? badgeImageData : this.badgeImageData;
		this.awardedTitle = awardedTitle != null ? awardedTitle : this.awardedTitle;
		this.selfIntro = selfIntro != null ? selfIntro : this.selfIntro;
		this.snsLink = snsLink != null ? snsLink : this.snsLink;
	}

	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;

		// 레벨 업데이트 로직 (관리자 제외)
		if (!"관리자".equals(this.level)) {
			if (activityScore < 10) {
				this.level = "씨앗";
			} else if (activityScore < 30) {
				this.level = "새싹";
			} else if (activityScore < 60) {
				this.level = "묘목";
			} else if (activityScore < 100) {
				this.level = "성목";
			} else {
				this.level = "고목";
			}
		}
	}
}