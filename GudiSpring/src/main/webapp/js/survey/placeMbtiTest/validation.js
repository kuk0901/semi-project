/**
 * 
 */

function userValidationFnc(path, areaNo, placeNo, userNo) {
  if (!userNo) {
    alert("로그인한 유저만 이용 가능합니다.");
    location.href = `${path}/auth/signin`;
    return;
  }
  
  location.href = `${path}/reservation/recommend?area=${areaNo}&place=${placeNo}&user=${userNo}`;
}