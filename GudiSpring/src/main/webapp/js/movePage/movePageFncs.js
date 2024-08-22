// main p
function moveMainPageFnc(path) {
	location.href = path;
}

// admin
function moveAdminMainPageFnc(port) {
	location.href = `http://localhost:${port}/GudiSpring/admin/main`;
}

function moveAdminPlaceListPageFnc(path) {
	location.href = `${path}/admin/place/list`;
}

function moveAdminAddPlacePageFnc(path) {
	location.href = `${path}/admin/place/add`;
}

function moveAdminEventListPageFnc(path) {
	location.href = `${path}/admin/event/list`;
}

function moveAdminAddEventPageFnc(path) {
	location.href = `${path}/admin/event/add`;
}

function moveAdminListPageFnc(path) {
	location.href = `${path}/admin/list`;
}

// signin, signup, signout
function moveSigninPageFnc(path) {
	location.href = `${path}/auth/signin`;
}

function moveSignupPageFnc(path) {
	location.href = `${path}/auth/signup`;
}

function moveLogoutPageFnc(path) {
	location.href = `${path}/auth/signout`;
}

function moveUserDetailPageFnc(path, userNo){
	location.href = `${path}/user/detail?userNo=` + userNo;
}

function moveUserPostDetailPageFnc(path, contentNo){
	location.href = `${path}/board/freeboard/detail?contentNo=` + contentNo;
}


// category
function movePensionAreaPageFnc(path) {
	location.href = `${path}/area/pension`;
}

function moveRestaurantAreaPageFnc(path) {
	location.href = `${path}/area/restaurant`;
}

function moveCafeAreaPageFnc(path) {
	location.href = `${path}/area/cafe`;
}

// board + notice
function moveFreeBoardListPageFnc(path) {
	location.href = `${path}/board/freeboard/list`;
}

function moveFreeBoardAddPageFnc(path) {
	location.href = `${path}/board/freeboard/add`;
}

function moveReviewBoardListPageFnc(path) {
	location.href = `${path}/board/reviewboard/list`;
}

function moveReviewBoardAddPageFnc(path) {
	location.href = `${path}/board/reviewboard/add`;
}

function moveNoticeListPageFnc(path) {
	location.href = `${path}/board/notice/list`;
}

function moveNoticeAddFnc(path) {
	location.href = `${path}/board/notice/add`;
}

// event
function moveEventListPageFnc(path) {
	location.href = `${path}/event/list`;
}

// survey
function movePlaceMbtiTestPageFnc(path) {
	location.href = `${path}/survey/placeMbtiTest`;
}
