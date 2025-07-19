<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<head>
	<title>회사 상세</title>
	<link rel="stylesheet" href="/dist/assets/css/company/companyView.css" >
</head>
<body>
	<div class="company-header n_inner">
	    <div class="company-header-container">
	        <div class="company-header-branding background-type1">
	            <div class="company-header-branding-container ">
	                <input type="hidden" id="mId" value="">
	                <input type="hidden" id="cId" value="">
	                <input type="hidden" id="jkcId" value="kari042">
	                <input type="hidden" id="devPath" value="https://www.jobkorea.co.kr">
	                <div class="logo"><a href="/company/1899862"><img src="//imgs.jobkorea.co.kr/img1/_whitebg/200X80/Co_Logo/Logo/2008/3/2R12v007Pq_dW2M2J3829q0dvMlS5_8Qvq.jpg?v=202506270920&amp;hash=r&amp;serviceCode=CL" onerror="companylogoOnError('//imgs.jobkorea.co.kr/img1/_whitebg/200X80/Co_Logo/Logo/2008/3/2R12v007Pq_dW2M2J3829q0dvMlS5_8Qvq.jpg?v=202506270920&amp;hash=r&amp;serviceCode=CL' , this)" alt="(재)한국항공우주연구원 로고" class="width" style="left:9px; margin:-21px 0 0 0"></a></div>
	
	
	                <div class="company-header-branding-body">
	                    <div class="special-feature">
	                        <div class="special-feature-item web-awards">
	                            <a href="javascript:void(0);" class="button button-tooltip"> i-AWARDS 수상</a>
	                        </div>
	                    </div>
	                    <div class="name">
	                        (재)한국항공우주연구원
	                    </div>
	                    <div class="summary">	
	                        <div class="summary-item">기타 공학 연구개발업 </div>
	                    </div>
	                </div>
	                <div class="add-ons">
	                    <div class="dibs">
	                        <a class="button button-dibs " memtype="C" data-mem-sys="366630" href="javascript:void(0);">
	                            <i class="icon" aria-hidden="true"></i>
	                            <div class="count" id="devFavCnt">2145</div>
	                        </a>
	                        <!-- [Dev] 툴팁 레이어 : 관심기업 off인 경우 노출 (클래스 open 추가/삭제) -->
	                        <div class="tooltip-box-wrap open">
	                            <p class="txt">관심기업 추가하고 <em>채용소식 받기</em></p>
	                            <a href="javascript:void(0);" class="btn-tooltip-close"><span class="skip">닫기</span></a>
	                        </div>
	                    </div>
	                    <div class="share">
	                        <a href="javascript:void(0);" class="button-share" data-target=".layer-share" aria-haspopup="true">공유</a>
	                        <div class="layer-share hidden" aria-hidden="true">
	                            <a href="javascript:goFacebook('http://joburl.kr/Zo3pI')" class="button button-facebook">페이스북</a>
	                            <a href="javascript:void(0);" class="button button-bookmark" id="devBookMark">즐겨찾기</a>
	                            <a href="javascript:void(0);" class="button button-copy-url button-popup-component" data-target=".layer-url-copied">URL복사</a>
	                        </div>
	                    </div>
	
	                    <div class="home">
	                        <a href="http://www.kari.re.kr" class="button button-home" target="_blank"></a>
	                    </div>
	
	                </div>
	            </div>
	        </div>
	
	
	        <div class="company-nav" role="navigation">
	            <div class="company-nav-container">
	                <a href="/company/1899862" class="company-nav-item active">
	                    <div class="name">기업정보</div>
	                    <div class="post">
	                        <div class="header screen-out">게시물</div>
	                        <div class="value"></div>
	                    </div>
	                </a>
	                
	                <a href="/company/1899862/Recruit" class="company-nav-item ">
	                    <div class="name">채용</div>
	                    <div class="post">
	                        <div class="header screen-out">게시물</div>
	                        <div class="value">0</div>
	                    </div>
	                </a>
	                
	                <a href="/company/1899862/salary" class="company-nav-item ">
	                    <div class="name">연봉정보</div>
	                    <div class="post">
	                        <div class="header screen-out">게시물</div>	
	                    </div>
	                </a>
	
	                <a href="/company/1899862/keyword" class="company-nav-item ">
	                    <div class="name">합격자소서</div>
	                    <div class="post">
	                        <div class="header screen-out">게시물</div>
	                        <div class="value">4</div>
	                    </div>
	                </a>
	            </div>
	        </div>
	    </div>
	</div>

	
	<div class="company-body mt-4" id="company-body">
	<div class="company-body-infomation inner">
	    <!--기업 사진/동영상 영역-->
	    <div class="company-infomation-row corporate-gallery">
	        <h2 class="header skip">기업 사진/동영상</h2>
	        <div class="company-infomation-container corporate-gallery-container">
	            <div class="company-gallery-viewer screen-out" aria-hidden="true">
	                <button type="button" class="button button-close">닫기</button>
	                <div id="gallery-tab-panel" class="panel" role="tabpanel" aria-hidden="true" aria-labelledby="gallery-tab">
	                    <div class="company-gallery-viewer-pagination"><span class="current">1</span>/<span class="total">4</span></div>
	                    <div class="company-gallery-viewer-container">
	                        <ul class="company-gallery-viewer-list" style="width:3200px" data-end-margin="-2400">
	                            <li class="company-gallery-viewer-item thumbnail js-lazy-img">
	                                <div class="js-lazy-img__inner display-table">
	                                    <div class="cell"><img src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg" alt="" class="thumbnail attatched" onerror="Company.Gallery.fileOnerror(this);" style="width: 515px; height: auto;"></div>
	                                </div>
	                                <div class="header">
	                                    <div class="header-inner">KSR-Ⅲ 발사</div>
	                                </div>
	                            </li>
	                            <li class="company-gallery-viewer-item thumbnail js-lazy-img">
	                                <div class="js-lazy-img__inner display-table">
	                                    <div class="cell"><img src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg" alt="" class="thumbnail attatched" onerror="Company.Gallery.fileOnerror(this);" style="width: 462px; height: auto;"></div>
	                                </div>
	                                <div class="header">
	                                    <div class="header-inner">아음속풍동</div>
	                                </div>
	                            </li>
	                            <li class="company-gallery-viewer-item thumbnail js-lazy-img">
	                                <div class="js-lazy-img__inner display-table">
	                                    <div class="cell"><img src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg" alt="" class="thumbnail attatched" onerror="Company.Gallery.fileOnerror(this);" style="width: 490px; height: auto;"></div>
	                                </div>
	                                <div class="header">
	                                    <div class="header-inner">기체구조시험</div>
	                                </div>
	                            </li>
	                            <li class="company-gallery-viewer-item thumbnail js-lazy-img">
	                                <div class="js-lazy-img__inner display-table">
	                                    <div class="cell"><img src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg" alt="" class="thumbnail attatched" onerror="Company.Gallery.fileOnerror(this);" style="width: 456px; height: auto;"></div>
	                                </div>
	                                <div class="header">
	                                    <div class="header-inner">기체구조시험동</div>
	                                </div>
	                            </li>
	                        </ul>
	                    </div><button type="button" class="button button-previous">이전</button><button type="button" class="button button-next">다음</button>
	                </div>
	            </div>
	            <div class="company-gallery-container">
	                <ul class="company-gallery-list">
	                    <li class="company-gallery-list-item thumbnail js-lazy-img"><a href="#company-gallery-viewer" data-category="undefined" data-index="1" class="js-lazy-img__inner"><img src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg" alt="" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg" class="attatched" onerror="Company.Gallery.fileOnerror(this);" >
	                            <div class="mask">
	                                <div class="mask-header">KSR-Ⅲ 발사</div>
	                            </div>
	                        </a></li>
	                    <li class="company-gallery-list-item thumbnail js-lazy-img"><a href="#company-gallery-viewer" data-category="undefined" data-index="2" class="js-lazy-img__inner"><img src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg" alt="" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg" class="attatched" onerror="Company.Gallery.fileOnerror(this);" >
	                            <div class="mask">
	                                <div class="mask-header">아음속풍동</div>
	                            </div>
	                        </a></li>
	                    <li class="company-gallery-list-item thumbnail js-lazy-img"><a href="#company-gallery-viewer" data-category="undefined" data-index="3" class="js-lazy-img__inner"><img src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg" alt="" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg" class="attatched" onerror="Company.Gallery.fileOnerror(this);" >
	                            <div class="mask">
	                                <div class="mask-header">기체구조시험</div>
	                            </div>
	                        </a></li>
	                    <li class="company-gallery-list-item thumbnail js-lazy-img"><a href="#company-gallery-viewer" data-category="undefined" data-index="4" class="js-lazy-img__inner"><img src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg" alt="" data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg" class="attatched" onerror="Company.Gallery.fileOnerror(this);" >
	                            <div class="mask">
	                                <div class="mask-header">기체구조시험동</div>
	                            </div>
	                        </a></li>
	                </ul>
	                <!-- [Dev]
	            <div class="mtcBtnB file-count"><span class=""></span></div>  -->
	            </div>
	        </div>
	    </div>
	    <!--기본정보 영역-->
	
	
	    <div class="company-infomation-row basic-infomation">
	        <h2 class="header">기업정보</h2>
	        <div class="company-infomation-container basic-infomation-container">
	
	            <table class="table-basic-infomation-primary" summary="해당 기업의 산업, 사원수, 기업구분, 설립일, 자본금, 매출액, 대표자, 주요사업, 4대보험, 주소, 홈페이지 등의 정보">
	                <caption class="table-caption">기업 상세 정보</caption>
	                <colgroup>
	                    <col class="col-label">
	                    <col class="col-value">
	                    <col class="col-label">
	                    <col class="col-value">
	                </colgroup>
	                <tbody>
	                    <tr class="field">
	                        <th class="field-label">산업</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div id="industryType" class="value"></div>
	                            </div>
	                        </td>
	
	                        <th class="field-label">사원수</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div class="values">
	                                    <div id="comMem" class="value"></div>
	                                    <div class="reference"></div>
	                                </div>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr class="field">
	
	                        <th class="field-label">기업구분</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div id="comType" class="value"></div>
	                            </div>
	                        </td>
	
	                        <th class="field-label">설립일</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div class="values">
	                                    <div id="comCreateYear" class="value"></div>
	                                    <div class="reference"></div>
	                                </div>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr class="field">
	
	                        <th class="field-label">자본금</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div class="values">
	
	                                    <div id="comCapital" class="value"></div>
	                                    <div class="reference"></div>
	                                </div>
	                            </div>
	                        </td>
	
	                        <th class="field-label">매출액</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div class="values">
	                                    <div class="value"></div>
	                                    <div class="reference"></div>
	                                </div>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr class="field">
	
	                        <th class="field-label">대표자</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div id="ceoName" class="value"></div>
	                            </div>
	                        </td>
	
	                        <th class="field-label">평균연봉</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div class="values">
	                                    <div class="salary-average">
	                                        <div class="salary-average-item"></div>
	                                    </div>
	                                    <a href="/company/1899862/salary" class="button button-view dev_new_pop"></a>
	                                </div>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr class="field">
	
	                        <th class="field-label">주요사업</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div id = "comMainBiz" class="value"></div>
	                            </div>
	                        </td>
	
	                        <th class="field-label">4대보험</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div id="insuranceYn" class="value"></div>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr class="field">
	
	                        <th class="field-label">홈페이지</th>
	                        <td class="field-value">
	                            <div class="value-container">
	                                <div id = "comUrl" class="value"></div>
	                            </div>
	                        </td>
	
	                        <th class="field-label">주소 </th>
	                        <td class="field-value" colspan="3">
	                            <div class="value-container">
	                                <div id="comAddr" class="value"></div>
	                            </div>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	    </div>
	
	    <!--재무분석 영역-->
	
	
	
	    <div class="company-infomation-row financial-analysis">
	        <h2 class="header">재무분석</h2>
	        <a href="javascript:void(0);" class="button button-view-financial-status">재무현황 전체보기</a>
	
	        <div class="company-infomation-container financial-analysis-container">
	            <div class="financial-analysis-card-wrap">
	
	                <!--사업분석-->
	                <!--매출액-->
	
	                <div class="financial-analysis-card">
	                    <div class="headers">
	                        <h3 class="header">매출액</h3>
	                        <div class="tooltip">
	                            <a href="#" class="activator tooltip-activator" aria-describedby="tooltip-revenue">툴팁</a>
	                            <div class="tooltip-popup" id="tooltip-revenue">
	                                <p>기업의 주요 영업활동 등을 통해 얻는 수익을 말합니다.<br>상품 등의 판매나 용역의 제공으로 얻어진 수익입니다.</p>
	                                <p class="source">[출처 : 네이버 지식백과 &gt; 용어해설 &gt; 매출액]</p>
	                                <button type="button" class="button button-close">닫기</button>
	                            </div>
	                        </div>
	                    </div>
	
	                    <div class="board-revenue">
	                        <div class="revenue">
	                            <div class="header">2022년 매출액 </div>
	                            <div class="value">5,611억 4천만원</div>
	                        </div>
	                        <div class="compared compared-last-year">
	                            <div class="header">작년 대비</div>
	                            <div class="values">
	                                <div class="value is-up">15%</div>
	                                <i class="icon icon-up">상승</i>
	                            </div>
	                        </div>
	
	                        <div class="compared compared-average-industry">
	                            <div class="header">업계평균 대비</div>
	                            <div class="values">
	                                <div class="value is-up">3138%</div>
	                                <i class="icon icon-up">상승</i>
	                            </div>
	                        </div>
	
	                    </div>
	
	                    <div class="chart-bar-wrap">
	                        <div class="chart chart-bar chart-bar-revenue chart-bar-plus">
	                            <div class="axios-y" aria-hidden="true">0</div>
	                            <div class="bar bar1">
	                                <div class="label-container">
	                                    <div class="label">2019</div>
	                                </div>
	                                <div class="progress" style="height:118px">
	                                    <div class="value">5,351억 5천만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar2">
	                                <div class="label-container">
	                                    <div class="label">2020</div>
	                                </div>
	                                <div class="progress" style="height:149px">
	                                    <div class="value">6,742억 2천만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar3">
	                                <div class="label-container">
	                                    <div class="label">2021</div>
	                                </div>
	                                <div class="progress" style="height:108px">
	                                    <div class="value">4,874억 8천만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar4 max">
	                                <div class="label-container">
	                                    <div class="label">2022</div>
	                                </div>
	                                <div class="progress" style="height:124px">
	                                    <div class="value">5,611억 4천만원</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="update">2022.12기준</div>
	
	                </div>
	
	
	                <!--영업이익-->
	
	
	                <div class="financial-analysis-card">
	                    <div class="headers">
	                        <h3 class="header">영업이익 </h3>
	                        <div class="tooltip">
	                            <a href="#" class="activator tooltip-activator" aria-describedby="tooltip-operation-income">툴팁</a>
	                            <div class="tooltip-popup" id="tooltip-operation-income">
	                                <p>매출액에서 매출원가를 빼고 얻은 총이익 중에서 일반 관리비와 판매비를 제외한 금액입니다.<br>순수하게 영업을 통해 벌어들인 이익을 말합니다.</p>
	                                <button type="button" class="button button-close">닫기</button>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="board-revenue">
	                        <div class="revenue">
	                            <div class="header">2022년 영업이익 </div>
	                            <div class="value">-61억 3천만원</div>
	                        </div>
	
	
	                    </div>
	                    <div class="chart-bar-wrap">
	                        <div class="chart chart-bar chart-bar-operation-income chart-bar-minus">
	                            <div class="axios-y" aria-hidden="true">0</div>
	                            <div class="bar bar1 minus">
	                                <div class="label-container">
	                                    <div class="label">2019 </div>
	                                </div>
	                                <div class="progress" style="height:75px">
	                                    <div class="value">-80억 7천만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar2 minus">
	                                <div class="label-container">
	                                    <div class="label">2020 </div>
	                                </div>
	                                <div class="progress" style="height:75px">
	                                    <div class="value">-80억 3천만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar3 minus">
	                                <div class="label-container">
	                                    <div class="label">2021 </div>
	                                </div>
	                                <div class="progress" style="height:46px">
	                                    <div class="value">-49억 2천만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar4 max minus">
	                                <div class="label-container">
	                                    <div class="label">2022 </div>
	                                </div>
	                                <div class="progress" style="height:57px">
	                                    <div class="value">-61억 3천만원</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="update">2022.12기준</div>
	
	                </div>
	                <!--당기순이익-->
	
	
	                <div class="financial-analysis-card">
	                    <div class="headers">
	                        <h3 class="header">당기순이익 </h3>
	                        <div class="tooltip">
	                            <a href="#" class="activator tooltip-activator" aria-describedby="tooltip-net-income">툴팁</a>
	                            <div class="tooltip-popup" id="tooltip-net-income">
	                                <p>일정 기간에 발생한 순이익을 말합니다.<br>순이익은 매출액에서 매출원가, 판매비, 관리비 등을 제외한 금액입니다. </p>
	                                <p class="source">[출처 : 네이버 지식백과 &gt; 용어해설 &gt; 당기순이익]</p>
	                                <button type="button" class="button button-close">닫기</button>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="board-revenue">
	                        <div class="revenue">
	                            <div class="header">2022년 당기순이익</div>
	                            <div class="value">-7,932만원</div>
	                        </div>
	
	
	
	                    </div>
	                    <div class="chart-bar-wrap">
	                        <div class="chart chart-bar chart-bar-net-income chart-bar-justify">
	                            <div class="axios-y" aria-hidden="true">0</div>
	                            <div class="bar bar1 minus">
	                                <div class="label-container">
	                                    <div class="label">2019</div>
	                                </div>
	                                <div class="progress" style="height:3px">
	                                    <div class="value">-400만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar2 minus">
	                                <div class="label-container">
	                                    <div class="label">2020</div>
	                                </div>
	                                <div class="progress" style="height:6px">
	                                    <div class="value">-943만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar3">
	                                <div class="label-container">
	                                    <div class="label">2021</div>
	                                </div>
	                                <div class="progress" style="height:46px">
	                                    <div class="value">3,666만원</div>
	                                </div>
	                            </div>
	                            <div class="bar bar4 max minus">
	                                <div class="label-container">
	                                    <div class="label">2022</div>
	                                </div>
	                                <div class="progress" style="height:51px">
	                                    <div class="value">-7,932만원</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="update">2022.12기준</div>
	
	                </div>
	
	                <!--산업 내 위치-->
	                <!--기업등급-->
	
	
	                <div class="financial-analysis-card">
	                    <div class="headers">
	                        <h3 class="header">기업등급 </h3>
	                    </div>
	                    <div class="chart-gauge" aria-hidden="true">
	                        <table class="skip" aria-hidden="true">
	                            <tbody>
	                                <tr>
	                                    <td class="value">양호 </td>
	                                </tr>
	                            </tbody>
	                        </table>
	                        <div class="needle class3"></div>
	                        <svg width="435" height="297">
	                            <g transform="translate(217.5, 148.5)">
	                                <path class="arc chart-color1" d="M-148.4981437538672,-0.7424969062538331A148.5,148.5,0,0,1,-128.97441335880936,-73.60605069388642L-89.022743227461,-50.8055232062179A102.5,102.5,0,0,0,-102.49871875266926,-0.5124978645859791Z"></path><text class="label" transform="translate(-121.2236911992781, -32.4817901603663)" dy=".35em" fill="#fff" style="text-anchor: middle;">위험</text>
	                                <path class="arc chart-color2" d="M-128.2319164525555,-74.89209305998081A148.5,148.5,0,0,1,-74.89209305998072,-128.23191645255557L-51.69319554645134,-88.51024536287504A102.5,102.5,0,0,0,-88.51024536287501,-51.6931955464514Z"></path><text class="label" transform="translate(-88.74190103891165, -88.74190103891178)" dy=".35em" fill="#fff" style="text-anchor: middle;">주의</text>
	                                <path class="arc chart-color3 active" d="M-73.60605069388646,-128.97441335880933A148.5,148.5,0,0,1,-0.7424969062538787,-148.4981437538672L-0.5124978645860105,-102.49871875266926A102.5,102.5,0,0,0,-50.80552320621793,-89.02274322746099Z"></path><text class="label" transform="translate(-32.48179016036634, -121.22369119927808)" dy=".35em" fill="#fff" style="text-anchor: middle;">양호</text>
	                                <path class="arc chart-color4" d="M0.7424969062538241,-148.4981437538672A148.5,148.5,0,0,1,73.60605069388642,-128.97441335880936L50.80552320621789,-89.022743227461A102.5,102.5,0,0,0,0.5124978645859729,-102.49871875266926Z"></path><text class="label" transform="translate(32.481790160366295, -121.2236911992781)" dy=".35em" fill="#fff" style="text-anchor: middle;">우수</text>
	                                <path class="arc chart-color5" d="M74.89209305998067,-128.2319164525556A148.5,148.5,0,0,1,128.23191645255548,-74.89209305998085L88.510245362875,-51.69319554645143A102.5,102.5,0,0,0,51.69319554645131,-88.51024536287507Z"></path><text class="label" transform="translate(88.74190103891162, -88.74190103891182)" dy=".35em" fill="#fff" style="text-anchor: middle;">상위</text>
	                                <path class="arc chart-color6" d="M128.97441335880927,-73.60605069388659A148.5,148.5,0,0,1,148.5,-1.6826650526014499e-13L102.5,-1.161435474017836e-13A102.5,102.5,0,0,0,89.02274322746095,-50.80552320621801Z"></path><text class="label" transform="translate(121.30451676625329, -32.178629742513536)" dy=".35em" fill="#fff" style="text-anchor: middle;">최상위</text>
	                            </g>
	                        </svg>
	                    </div>
	                    <div class="rating">
	                        <div class="header">
	                            <div class="header-container">
	                                <strong>양호</strong>등급
	                            </div>
	                        </div>
	                        <div class="description">
	                            <div class="description-container">신용능력이 보통입니다. 외부 환경 변화에 대처 가능하나, 경제 여건 및 환경 변화에 따른 영향을 받을 가능성이 높습니다. </div>
	                        </div>
	                    </div>
	                    <div class="based-on-data">NICE평가정보 자료를 바탕으로 구성되었습니다.</div>
	
	                </div>
	                <!--총자산 증가율-->
	                <!--동종업계 순위-->
	
	
	                <div class="financial-analysis-card financial-analysis-card-ranking full-width">
	                    <div class="headers" id="devRankIndustry">
	                        <h3 class="header">동종업계 순위</h3>
	                        <button type="button" class="button button-view-ranking-more">동종업계 더보기</button>
	                    </div>
	                    <div class="financial-analysis-card-container">
	                        <div class="ranking-peers">
	                            <div class="ranking">
	                                <div class="ranking-container">
	                                    <div class="ranking-header">동종업계</div>
	                                    <div class="ranking-value"><strong>2</strong>위</div>
	                                </div>
	                            </div>
	                        </div>
	                        <ol class="list list-ranking-peers">
	                            <li class="">
	                                <div class="order">
	                                    1위
	                                </div>
	                                <div class="company">
	                                    <a href="/company/1642064" target="_blank">
	                                        한국산업기술기획평가원
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="current">
	                                <div class="order">
	                                    2위
	                                </div>
	                                <div class="company">
	                                    (재)한국항공우주연구원 </div>
	                            </li>
	                            <li class="">
	                                <div class="order">
	                                    3위
	                                </div>
	                                <div class="company">
	                                    <a href="/company/44959175" target="_blank">
	                                        지엠테크니컬센터코리아(주)
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="">
	                                <div class="order">
	                                    4위
	                                </div>
	                                <div class="company">
	                                    <a href="/company/1850794" target="_blank">
	                                        (재)한국원자력연구원
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="">
	                                <div class="order">
	                                    5위
	                                </div>
	                                <div class="company">
	                                    <a href="/company/1544107" target="_blank">
	                                        한국생산기술연구원
	                                    </a>
	                                </div>
	                            </li>
	                        </ol>
	                        <ol class="list list-ranking-peers">
	                            <li class="">
	                                <div class="order">6위</div>
	                                <div class="company">
	                                    <a href="/company/16152311" target="_blank">
	                                        고려대학교산학협력단
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="">
	                                <div class="order">7위</div>
	                                <div class="company">
	                                    <a href="/company/1830354" target="_blank">
	                                        (재)한국에너지기술연구원
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="">
	                                <div class="order">8위</div>
	                                <div class="company">
	                                    <a href="/company/1969855" target="_blank">
	                                        (재)한국표준과학연구원
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="">
	                                <div class="order">9위</div>
	                                <div class="company">
	                                    <a href="/company/45438928" target="_blank">
	                                        전북대학교산학협력단
	                                    </a>
	                                </div>
	                            </li>
	                            <li class="">
	                                <div class="order">10위</div>
	                                <div class="company">
	                                    <a href="/company/1864249" target="_blank">
	                                        한국과학기술정보연구원
	                                    </a>
	                                </div>
	                            </li>
	                        </ol>
	                    </div>
	
	                    <div class="based-on-data">표준산업 분류 기준 [기타 공학 연구개발업]의 공시된 재무정보(매출액)를 기준으로 평가된 순위입니다.</div>
	
	                    <div class="button button-view-reference-table">2019기준 </div>
	
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="ad ad-horizontal">
	
	
	        <script type="text/javascript">
	            // AD 배너 이미지 오류
	            function adBnnrImageOnError_17(targetBnnr, pageTypeCode, bnnrIdx) {
	                var imgTag = ".imgAdBnnr_" + pageTypeCode + "_" + bnnrIdx;
	                var divTag = ".divAdBnnr_" + pageTypeCode + "_" + bnnrIdx;
	
	                var defImgUrl = "";
	                var defImgLink = "https://www.jobkorea.co.kr/service/company/ad";
	                var defImgWidth = 0;
	                var defImgHeight = 0;
	
	                if (defImgUrl != "") {
	                    var img = new Image();
	                    img.addEventListener('error', function(e) {
	                        $(imgTag).removeClass("imgAdBnnr");
	                        $(divTag).css("display", "none");
	                    });
	                    img.addEventListener('load', function() {
	                        targetBnnr.src = defImgUrl;
	
	                        $(imgTag).addClass("IsDefault");
	                        $(imgTag).attr("target", "_blank");
	                        $(imgTag).attr("href", defImgLink);
	
	                        $(divTag).css("width", defImgWidth);
	                        $(divTag).css("height", defImgHeight);
	                    });
	                    img.src = defImgUrl;
	                } else {
	                    $(divTag).css("display", "none");
	                }
	            }
	        </script>
	        <div class="ad-container divAdBnnr_17_0" style="width:1040px; height:80px; background-color:#ffffff;">
	            <a class="imgAdBnnr imgAdBnnr_17_0" href="javascript:;" data-campaignid="189" data-campaignproductid="673" data-space="corpInfoMiddle" data-urlpath="/company/1899862" data-linkurl="https://forms.gle/vHVS3SpxVYHPvspd7" data-linktype="NEW_WINDOW" data-gender="" data-age="0" data-pagetypecode="17">
	
	                <img src="https://ads.jobkorea.co.kr/ads/pc/{service}/202505/572ff5ec-3d13-4866-bd5b-0a4ae0ada07a.png" alt="잠재성장청년 전용관" onerror="adBnnrImageOnError_17(this, 17, 0)">
	            </a>
	        </div>
	
	    </div>
	    <!--기업분석 / 1000대기업만 노출-->
	    <!--기업분석 탭전환 작업 (기획자:안지현)-->
	
	
	
	
	    <div class="company-infomation-row corporate-issues">
	        <h2 class="header">기업이슈</h2>
	
	
	        <!--true 면 nice , false면 jk -->
	        <div class="company-infomation-container corporate-issues-container corporate-history fixed-height">
	            <h3 class="header">연혁</h3>
	            <div class="corporate-history-list">
	                <div class="corporate-history-list-item">
	                    <div class="year">2008 </div>
	
	                    <div class="list-month">
	                        <div class="month">03</div>
	                        <div class="month-description">기추기술연구회로 신규 편입</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">2005 </div>
	
	                    <div class="list-month">
	                        <div class="month">12</div>
	                        <div class="month-description">원장 채연석 퇴임 후 대표이사 백홍열 취임</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">2004 </div>
	
	                    <div class="list-month">
	                        <div class="month">10</div>
	                        <div class="month-description">설립근거 변경(과학기술분야 정부출연 연구기관 등의 설립.운영 및 육성에 관한 법률)</div>
	                        <div class="month-description">우주센터 기공식</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">2003 </div>
	
	                    <div class="list-month">
	                        <div class="month">08</div>
	                        <div class="month-description">항공기체계종합/성능시험센터 기공식</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">2002 </div>
	
	                    <div class="list-month">
	                        <div class="month">12</div>
	                        <div class="month-description">스마트무인기술개발사업단 개소식</div>
	                        <div class="month-description">원장 최동환 퇴임 후 채연석 취임</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">2001 </div>
	
	                    <div class="list-month">
	                        <div class="month">09</div>
	                        <div class="month-description">본점 이전 : 대전 유성구 어은동 45</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	
	                    <div class="list-month">
	                        <div class="month">01</div>
	                        <div class="month-description">재단명 변경 : (재)한국항공우주연구원</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">1999 </div>
	
	                    <div class="list-month">
	                        <div class="month">12</div>
	                        <div class="month-description">제 5대 소장 최동환 취임</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	
	                    <div class="list-month">
	                        <div class="month">05</div>
	                        <div class="month-description">방위산업 전문연구기관 위촉(국방부)</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	
	                    <div class="list-month">
	                        <div class="month">01</div>
	                        <div class="month-description">국무총리실산하 공공기술연구회로 소속변경(한국항공우주연구소 설립근거 변경)</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">1997 </div>
	
	                    <div class="list-month">
	                        <div class="month">04</div>
	                        <div class="month-description">건설교통부 항공법에 의한 [형식증명 전문기관]지정</div>
	                    </div>
	                </div>
	                <div class="corporate-history-list-item">
	                    <div class="year">1996 </div>
	
	                    <div class="list-month">
	                        <div class="month">11</div>
	                        <div class="month-description">(재)한국항공우주연구소 설립</div>
	                    </div>
	                </div>
	            </div>
	            <a href="javascript:void(0);" class="button button-more-history">더보기</a>
	        </div>
	
	
	        <div class="popup popup-latest-news hidden" aria-hidden="true">
	
	        </div>
	    </div>
	
	    <div class="detail">
	        <p class="txt">더 상세한 기업정보를 원하시나요?<a href="https://www.nicebizinfo.com/ep/EP0100M002GE.nice?kiscode=123208&amp;siteid=JOBKR" target="_blank">상세기업정보 확인하기(유료)</a></p>
	        <p class="nice-txt">Nice 평가정보</p>
	    </div>
	    <!--고용현황-->
	
	    <div class="company-infomation-row employment-status">
	        <h2 class="header">고용현황 </h2>
	        <div class="company-infomation-container employment-status-container">
	
	            <div class="employment-status-card-wrap  ">
	                <div class="employment-status-card recruit-history">
	                    <div class="headers">
	                        <h3 class="header">채용 History</h3>
	                    </div>
	                    <div class="recruit-history-description-container">
	                        <div class="recruit-history-description">
	                            <div class="total">33회의 채용 중</div>
	                            <div class="of"><strong>정규직 채용</strong>은 <strong>2회</strong>입니다.</div>
	                        </div>	
	                    </div>
	                    <div class="chart chart-doughnut-recruit-history">
	                        <table class="skip" aria-hidden="true">
	                            <tbody>
	                                <tr>
	                                    <th>신입,경력 </th>
	                                    <td>27</td>
	                                </tr>
	                                <tr>
	                                    <th>신입 </th>
	                                    <td>6</td>
	                                </tr>	
	                            </tbody>
	                        </table>
	                        <h3 class="header"><strong>최근 3년간</strong><br>채용 횟수</h3>
	                        <svg class="chart-svg" width="164" height="164">
	                            <g transform="translate(82,82)">
	                                <circle cx="0" cy="0" r="82" fill="#fff"></circle>
	                                <g class="arc">
	                                    <path d="M5.021051876504148e-15,-82A82,82,0,1,1,-74.1958183022136,-34.91390190833596L-44.33652557083495,-20.86318528668856A49,49,0,1,0,3.0003846579110155e-15,-49Z" fill="#78c1ff"></path><text transform="translate(35.096655071124275,55.303479120381986)" dy=".35em" fill="#fff" style="text-anchor: middle; font: bold 13px Verdana;">27</text><text class="label" dy=".35em" fill="#78c1ff" text-anchor="start" transform="translate(49.13531709957398, 77.42487076853477)">
	                                        <tspan x="0" y="0">신입</tspan>
	                                        <tspan x="0" y="20">경력 </tspan>
	                                    </text>
	                                </g>
	                                <g class="arc">
	                                    <path d="M-74.1958183022136,-34.91390190833596A82,82,0,0,1,-1.5063155629512442e-14,-82L-9.001153973733046e-15,-49A49,49,0,0,0,-44.33652557083495,-20.86318528668856Z" fill="#ff6d6d"></path><text transform="translate(-35.09665507112431,-55.30347912038197)" dy=".35em" fill="#fff" style="text-anchor: middle; font: bold 13px Verdana;">6</text><text class="label" dy=".35em" fill="#ff6d6d" text-anchor="end" transform="translate(-49.13531709957403, -77.42487076853476)">
	                                        <tspan x="0" y="0">신입 </tspan>
	                                    </text>
	                                </g>
	                            </g>
	                        </svg>
	                    </div>
	                    <div class="benchmark">최근 3년 기준</div>
	
	                </div>
	                <div class="employment-status-card number-of-employees">
	                    <div class="headers">
	                        <h3 class="header">사원수</h3>
	                    </div>
	                    <div class="chart-bar-wrap">
	                        <div class="chart chart-bar chart-bar-number-of-employees chart-bar-plus">
	                            <div class="axios-y" aria-hidden="true">0</div>
	                            <div class="bar bar1">
	                                <div class="label-container">
	                                    <div class="label">2002</div>
	                                </div>
	                                <div class="progress" style="height:69px">
	                                    <div class="value">317명</div>
	                                </div>
	                            </div>
	                            <div class="bar bar2">
	                                <div class="label-container">
	                                    <div class="label">2003</div>
	                                </div>
	                                <div class="progress" style="height:69px">
	                                    <div class="value">317명</div>
	                                </div>
	                            </div>
	                            <div class="bar bar3">
	                                <div class="label-container">
	                                    <div class="label">2004</div>
	                                </div>
	                                <div class="progress" style="height:112px">
	                                    <div class="value">518명</div>
	                                </div>
	                            </div>
	                            <div class="bar bar4 max">
	                                <div class="label-container">
	                                    <div class="label">2007</div>
	                                </div>
	                                <div class="progress" style="height:149px">
	                                    <div class="value">690명</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="benchmark">최근 4년 기준</div>	
	                </div>
	
	                <div class="employment-status-card employment-status-card-in-progress">	
	                    <div class="headers">
	                        <h3 class="header">채용공고</h3>
	                    </div>
	                    <table class="table table-in-progress-announcement">
	                        <caption><span class="skip"></span></caption>
	                        <thead>
	                            <tr>
	                                <th scope="col" class="th-term">접수기간</th>
	                                <th scope="col" class="th-title">채용제목</th>
	                                <th scope="col" class="th-division">구분</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <tr>
	                                <td colspan="3">진행중인 채용공고가 없습니다.</td>
	                            </tr>
	                        </tbody>	
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!--근무환경-->
	
	
	    <div class="company-infomation-row working-environment">
	        <h2 class="header">근무환경</h2>
	
	        <!--기업소개-->
	
	
	        <div class="company-infomation-container working-environment-container working-environment-introduce fixed-height">
	            <h3 class="header">기업소개</h3>
	            <div class="introduce-body">
	                <p id="devsummary"></p>
	                <div align="justify">
	                    <div align="justify"><strong>한국항공우주연구원</strong>은 1989년 설립 이래 우리나라 항공우주 분야의 연구개발을 주도하는 동시에 국가 항공우주 대표 기관으로서의 기능과 역할을 수행하기 위해 최선의 노력을 기울여 왔습니다. 한국항공우주연구원의 연구개발은 크게 첨단 항공기 개발, 인공위성 개발, 우주발사체 개발로 나뉘며, 국가의 법적위임을 받아 항공기 및 우주기기의 품질인증 업무도 수행하고 있습니다.<br><br>대표적인 첨단 항공기 개발에는 스마트 무인기 개발사업과 한국형 헬리콥터 개발사업이 있고, 인공위성 분야는 다목적 실용위성 2호의 개발이 마무리 단계에 있으며, 현재 다목적 실용위성 3호와 5호, 그리고 통신해양기상위성의 개발이 한창 진행 중에 있습니다. 우주발사체 개발을 위해서는 2002년 액체추진기관을 사용한 과학로켓 KSR-Ⅲ의 시험발사에 성공하는 등 기초기술력 향상을 위한 프로그램을 꾸준히 수행해 왔으며, 이러한 기술력을 바탕으로 현재는 2007년 발사를 목표로 한 100kg급 저궤도 소형위성 발사체인 KSLV-Ⅰ의 개발에 주력하고 있습니다. <br><br>우주발사체의 개발과 더불어 2007년에는 우주발사장인 우주센터의 건설도 완공될 예정에 있어 명실상부한 우주개발국으로의 진입이 기대되는 바입니다. 아울러 2007년에는 한국인 최초의 우주비행사가 탄생되어 다음 세대에게 미래를 향한 꿈과 희망을 심어주게 될 것입니다. 한국항공우주연구원은 우리나라의 항공우주 기술개발을 선도하고, 이를 통하여 우리나라 항공우주 산업 경쟁력을 제고함으로써, 향후 10년 내에 항공우주 분야에 있어 세계적인 초일류 연구기관으로 발돋움하기 위하여 열과 성을 다할 것을 다짐합니다.<br><br>[<strong>KARI 비전</strong>]<br>
	                        <center><img src="https://file1.jobkorea.co.kr/co_logo/logo2/newtype/ex/kari-e.jpg"></center><br><br>[<strong>연구원 임무 및 기능</strong>]<br>1. 항공기, 인공위성, 우주발사체의 종합시스템 및 핵심기술 연구개발과 실용화<br> • 선도기술 학공기 개발, 항공기의 시험평가 및 국가개발사업 지원<br> • 인공위성 연구개발 발사 및 위성 이용기술 개발<br> • 우주발사체시스템 개발 및 우주센터 운영<br><br>2. 항공우주 안전성 및 품질 확보를 위한 기술개발, 항공우주 생산품의 법적 품질인증 <br> 및 국가간 상호인증<br>3. 국가항공우주개발 정책수립 지원, 항공우주 기술정보의 유통 및 보급 ‧ 확산<br>4. 시험평가시설의 산 ‧ 학 ‧ 연 공동활용, 연구개발성과의 기술이전 및 기업화 지원,<br> 기술협력 및 교육훈련<br><br>[<strong>주요연구개발 분야</strong>]<br>
	                        <center><img src="https://file1.jobkorea.co.kr/co_logo/logo2/newtype/ex/kari2-e.jpg"></center><br><br>[<strong>조직도</strong>]<br>
	                        <center><img src="https://file1.jobkorea.co.kr/co_logo/logo2/sy/sy1/kari_org.gif"></center>
	                    </div>
	                </div>
	                <p></p>
	            </div>
	            <a href="javascript:void(0);" class="button button-more-introduce">더보기</a>
	        </div>
	
	        <!--직무 인터뷰: Company일때만 노출-->
	
	        <!--복리후생-->
	        <div class="company-infomation-container working-environment-container working-environment-benefit">
			    <h3 class="header">복리후생</h3>
			    <div class="benefit-list">
			        <div class="benefit-item-group">
			            <div class="item pension">
			                <div class="benefit-header"> 연금·보험</div>
			                <div class="benefit-body">
			                    <p>국민연금 </p>
			                    <p>고용보험</p>
			                    <p>산재보험</p>
			                    <p>건강보험</p>
			                </div>
			            </div>
			            <div class="item refresh">
			                <div class="benefit-header"> 휴무·휴가·행사</div>
			                <div class="benefit-body">
			                    <p>연차제도</p>
			                    <p>월차제도</p>
			                    <p>정기휴가</p>
			                    <p>경조휴가</p>
			                    <p>창립일 휴무</p>
			                    <p>노동절 휴무</p>
			                </div>
			            </div>
			            <div class="item compensation-support">
			                <div class="benefit-header"> 보상·수당·지원</div>
			                <div class="benefit-body">
			                    <p>퇴직금</p>
			                    <p>경조금</p>
			                    <p>가족 의료비 지원</p>
			                    <p>연월차제도수당</p>
			                    <p>명절선물/명절상여</p>
			                    <p>휴가비</p>
			                    <p>임직원 할인</p>
			                </div>
			            </div>
			        </div>
			        <div class="benefit-item-group">
			            <div class="item life">
			                <div class="benefit-header"> 편의·여가·건강</div>
			                <div class="benefit-body">
			                    <p>건강검진</p>
			                    <p>점심 식사 지원</p>
			                    <p>근무복 지원</p>
			                    <p>휴양시설</p>
			                </div>
			            </div>
			        </div>
			    </div>			
			</div>
	
	        <!--기업위치 //jquery는 최대한 브라우저 상단에 넣는 것이 좋음. 다시 만나면, 뒤집어 쓰게 된다-->
	        <div class="company-infomation-container working-environment-container working-environment-map">
	            <h3 class="header">기업위치</h3>
	
	            <div class="address">대전 유성구 어은동 45번지 3층</div>
	
	            <a href="https://maps.naver.com/?mapMode=0&amp;dlevel=12&amp;pinTitle=%ed%95%9c%ea%b5%ad%ed%95%ad%ea%b3%b5%ec%9a%b0%ec%a3%bc%ec%97%b0%ea%b5%ac%ec%9b%90&amp;lat=36.3762892117961&amp;lng=127.354306544959" target="_blank" title="새창" class="btnMapApiL"><span>지도보기</span></a>
	        </div>
	
	    </div>
	
	    <div class="bottom-banner review">
	        <a href="/review/home" onclick="GA_Event('기업정보_PC', '기업하단배너', '기업리뷰작성하기'); BrazeCallPageInto('기업리뷰진입');" target="_blank">
	            <img src="//i.jobkorea.kr/content/images/company/review/banner2_1040x80.png" alt="취업&amp;이직 준비중이라면? 현직자가 알려주는 기업리뷰 체크는 필수!기업리뷰 바로가기">
	        </a>
	    </div>
	    <!--하단-->
	
	
	
	    <div class="license">
	        <div class="license-description">띹잡 기업정보와 NICE평가정보 기업정보를 기반으로 기업 프로필을 제공합니다. 잘못된 정보는 신고해주시면 빠르게 전달하여 수정 검토하겠습니다.<br>게시된 정보는 무단으로 수집 및 배포할 수 없습니다.</div>
	        <div class="license-help">
	            <div class="license-help-item header">자료수정 및 정정문의</div>
	            <div class="license-help-item tel">띹잡 고객센터 (T. 1588-9350, E. help@ddit.co.kr)</div>
	        </div>
	    </div>
	</div>
	</div>
</body>
</html>