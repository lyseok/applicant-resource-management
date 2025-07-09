<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<header id="dashHeader" class="dash_header">
    <div class="wrap_top_btns">
        <div class="wrap_my_layer">
            <button type="button" class="top_btn btn_alarm" id="myAlarmBlt">
            	<span class="blind">내알림 레이어 버튼</span>
            	<i class='bx  bx-bell'  ></i> 
            </button>
            <div class="top_layer_assist alarm_layer_content" id="displayAlBtn" style="display:none;">
                <strong class="title">알림</strong>
                <span class="view_status">최근 30일간 저장내역 노출</span>
                <div class="save_list_area ">
	                <div class="my_activity" id="my-activity"> <strong class="sub_title">나의 활동</strong>
	
					    <div class="alarm_list">
					
					        <div class="save_list">
					            <span class="date">06/30(월) 00:00</span>
					            <ul>
					                <li class="activity">
					                	<a href=""><span class="activity_name">이력서 등록 완료</span></a>
					                </li>
					            </ul>
					        </div>
						</div>
	            	</div>
        		</div>
    		</div>
    	</div>
   	</div>

    <div class="wrap_member">
<!--         <button type="button" class="btn_member"> -->
<!--             <span class="user_photo"> -->
<!--                 <span id="gnb_personnal_photo_area_span" class="img"></span> -->
<!--             </span> -->
<!--             <span class="user_name">userName</span> -->
<!--             <i class='bx  bx-caret-down'  ></i>  -->
<!--         </button> -->
		

		<button type="button" class="btn_member">
		    <span class="user_photo">
		        <span id="gnb_personnal_photo_area_span" class="img"></span>
		    </span>
		    <span class="user_name">
		        <c:choose>
		            <c:when test="${userType eq 'company'}">
		                ${comName}
		            </c:when>
		            <c:when test="${userType eq 'admin'}">
		                관리자
		            </c:when>
		            <c:when test="${userType eq 'member'}">
		                ${memberName}
		            </c:when>
		            <c:otherwise>
		                일반유저
		            </c:otherwise>
		        </c:choose>
		    </span>
		    <i class='bx bx-caret-down'></i>
		</button>
        <div class="layer_member" id="displayMemBtn" style="display:none;">
            <ul>
                <li>
                    <a href="/zf_user/persons/person-update" target="_blank">
                        <i class='bx  bx-cog'  ></i> 
                        <span class="txt">계정정보 설정</span>
                    </a>
                </li>
                <li>
                    <a href="/zf_user/member/set-incoming-email">
                        <i class='bx  bx-envelope-alt'  ></i> 
                        <span class="txt">메일 수신 설정</span>
                    </a>
                </li>
                <li>
                    <a href="/zf_user/persons/edit-passwd" target="_blank">
                        <i class='bx  bx-lock-keyhole'  ></i> 
                        <span class="txt">비밀번호 변경</span>
                    </a>
                </li>
                <li>
                    <a href="/zf_user/member/my-logins" target="_blank">
                        <i class='bx  bx-car-key'  ></i> 
                        <span class="txt">로그인 관리</span>
                    </a>
                </li>
                <li>
                    <div class="logoutBtn">
                        <a href="javascript:void(0)">
                            <i class='bx  bx-door-open'  ></i> 
                            <span class="txt">로그아웃</span>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</header>