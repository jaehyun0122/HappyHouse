// import { API_KAKAO_MAP_URL } from "@/config";

// 본인 appkey
const appkey = "2cdca694d8a3307b68bf4a3fe6c90567";
const API_KAKAO_MAP_URL = "//dapi.kakao.com/v2/maps/sdk.js?";
// 카카오 지도 api 라이브러리 불러오기(지도 보일 컴포넌트 mounted에 쓰면됩니다.)
// ex) <script src = "카카오맵라이브러리"></script>
function load() {
  // 이미 라이브러리 불러왔다면 지도 그리기.
  if (window.kakao && window.kakao.maps) {
    // displayMap();
  } else {
    const script = document.createElement("script");
    // console.log(`${API_KAKAO_MAP_URL}`);
    script.src = `${API_KAKAO_MAP_URL}appkey=${appkey}&libraries=services&autoload=false`;
    document.head.appendChild(script);
    script.onload = () => kakao.maps.load(displayMap());
  }
}

// 지도 생성(검색하지 않았을 때)
function displayMap() {
  // 초기 kakao map 설정 start
  let map;
  //지도를 담을 영역의 DOM 레퍼런스
  // 지도를 그릴 노드 선택하시면 됩니다.
  const container = document.querySelector(".map-container");

  kakao.maps.load(function() {
    // 지도의 중심좌표(위,경도)
    // 마음에 드는 곳으로 위경도 바꾸셔도됩니다.
    const locPosition = new kakao.maps.LatLng(37.5012743, 127.039585); // (멀티캠퍼스)
    const options = {
      //지도를 생성할 때 필요한 기본 옵션
      center: locPosition, //지도의 중심좌표.
      level: 3 //지도의 레벨(확대, 축소 정도)
    };
    map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    // 지도를 중심좌표로 옮기기
    map.setCenter(locPosition);
    let marker; //eslint-disable-line no-unused-vars

    // 결과값으로 받은 위치를 마커로 표시합니다
    marker = new kakao.maps.Marker({
      map: map,
      position: locPosition
    });
  });
}

// 위,경도 좌표넣기 위한 변수
let positions = [];

// 지역을 검색한 결과로 지도 그리기
// 검색하고 난 후, 검색결과를 인자로 주어서 주소로 위,경도 구한 후 지도 그리기
// 전 houses를 Map 자료구조로 줬는데 배열로 줘도 됩니다!
// watch 또는 computed에서 사용하면 됩니다.
function makeMap(houses) {
  // 주소를 위,경도로 변환하기 위한 객체
  let geocoder = new kakao.maps.services.Geocoder();
  // 기존에 검색했던 위,경도 초기화
  positions = [];
  console.log(houses);
  // 여기서 전 Map 자료구조 써서 이터레이터 구했는데, 배열로 하면 이럴필요 없습니다.
  // let itr = houses.keys();
  // let key = "";
  // geocoder.addressSearch가 비동기라서, 검색 결과 개수만큼 반복했을 때, 지도를 그리기 위한 변수
  let cnt = 0;

  // 검색 결과 개수만큼 반복
  // 마찬가지로 배열이라면 for문 돌리셔도 됩니다.
  // while ((key = itr.next().value)) {
  for (let item = 0; item < houses.length; item++) {
    // 아파트
    let apt = houses[item];
    console.log(apt);

    // 아파트의 주소(도로명 + 도로명건물본번호코드)
    // 앞에 00012 이런식으로 되있는걸 12로 바꿔서 더해줍니다.
    // console.log("도로명" + " " + apt["도로명"]);
    let address =
      apt["도로명"] + " " + apt["도로명건물본번호코드"].replace(/(^0+)/, "");

    // 주소를 위,경도 좌표로 변환하는 함수
    geocoder.addressSearch(address, function(result, status) {
      // 정상적으로 응답을 받았다면
      if (status === kakao.maps.services.Status.OK) {
        // 개수를 증가시키고
        cnt++;
        // content에는 마커 위에 표시할 html을 넣어주시면 됩니다.
        // let content = `<div>${apt["아파트"]}</div>`;
        let content = `<div class="wrap">
            <div class="placeinfo">
            <div class="close" onclick="closeOverlay()" title="닫기"></div>
                <div class="title">
                    아파트명 : ${apt["아파트"]}
                    <div class="close" onclick="closeOverlay()" title="닫기"></div>
                </div>
                <div class="body">
                    <div class="desc">
                        <div class="ellipsis">법정동 : ${apt["법정동"]} </div>
                    </div>
                    <div class="desc">
                        <div class="ellipsis">${apt["층"]} 층 </div>
                    </div>
                    <div class="desc">
                        <div class="ellipsis">${apt["거래금액"]} 만원</div>
                    </div>
                    <div class="desc">
                        <div class="ellipsis">${address} </div>
                    </div>
                </div>
            </div>
        </div>`;
        let iwRemoveable = true;
        // ##################### custom overay ###################################
        // 커스텀 오버레이에 표시할 내용입니다
        // HTML 문자열 또는 Dom Element 입니다
        // 위,경도

        let latlng = new kakao.maps.LatLng(result[0].y, result[0].x);
        // 마커에 표시할 텍스트와 위,경도 넣기
        positions.push({ content, latlng, iwRemoveable });
      }
      // 검색결과 만큼 위,경도를 얻었다면
      if (cnt == houses.length) {
        // 지도그리기
        houseResultDisplayMap();
      }
    });
  }
}

// 검색결과로 지도를 그리고, 각각의 이벤트 추기
function houseResultDisplayMap() {
  // 초기 kakao map 설정 start
  //지도를 담을 영역의 DOM 레퍼런스
  // 지도를 그릴 노드 선택하시면 됩니다.
  const container = document.querySelector(".map-container");
  let map = new kakao.maps.Map(container, {
    level: 3, //지도의 레벨(확대, 축소 정도)
    center: new kakao.maps.LatLng(33.450701, 126.570667)
  });

  // 검색결과들의 중앙 위치
  let bounds = new kakao.maps.LatLngBounds();

  // 검색 결과들만큼 반복
  for (let i = 0; i < positions.length; i++) {
    // 바운드 추가
    bounds.extend(positions[i].latlng);

    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].latlng // 마커의 위치
    });

    // =================== category ============================

    // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
    var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
      contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      markers = [], // 마커를 담을 배열입니다
      currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다
    // 장소 검색 객체를 생성합니다
    var ps = new kakao.maps.services.Places(map);

    // 지도에 idle 이벤트를 등록합니다
    kakao.maps.event.addListener(map, "idle", searchPlaces);

    // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
    contentNode.className = "placeinfo_wrap";
    // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
    // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
    addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
    addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

    // 커스텀 오버레이 컨텐츠를 설정합니다
    placeOverlay.setContent(contentNode);

    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent();

    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    function addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    }
    // 카테고리 검색을 요청하는 함수입니다
    function searchPlaces() {
      if (!currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      removeMarker();

      ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
    }

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    }

    // 지도에 마커를 표출하는 함수입니다
    function displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(currCategory)
        .getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order
        );

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        (function(marker, place) {
          kakao.maps.event.addListener(marker, "click", function() {
            displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    }

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, order) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage
        });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    }

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      markers = [];
    }

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    function displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      contentNode.innerHTML = content;
      placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      placeOverlay.setMap(map);
    }

    // 각 카테고리에 클릭 이벤트를 등록합니다
    function addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;

      for (var i = 0; i < children.length; i++) {
        children[i].onclick = onClickCategory;
      }
    }

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    function onClickCategory() {
      var id = this.id,
        className = this.className;
      placeOverlay.setMap(null);
      if (className === "on") {
        currCategory = "";
        changeCategoryClass();
        removeMarker();
      } else {
        currCategory = id;
        changeCategoryClass(this);
        searchPlaces();
      }
    }

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    function changeCategoryClass(el) {
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.className = "on";
      }
    }

    // ================ category ===============================

    // 마커에 표시할 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
      content: positions[i].content, // 인포윈도우에 표시할 내용
      removable: positions[i].iwRemoveable
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    // kakao.maps.event.addListener(
    //   marker,
    //   "mouseover",
    //   makeOverListener(map, marker, infowindow)
    // );
    // kakao.maps.event.addListener(
    //   marker,
    //   "mouseout",
    //   makeOutListener(infowindow)
    // );
    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, "click", function() {
      // 마커 위에 인포윈도우를 표시합니다
      infowindow.open(map, marker);
    });

    // overay

    // overay

    // 마커 클릭시 지도 중심 좌표 옮기기
    kakao.maps.event.addListener(marker, "click", function() {
      // 위,경도
      let x = marker.getPosition().La;
      let y = marker.getPosition().Ma;
      let latlng = new kakao.maps.LatLng(y, x);
      // 클릭한 곳으로 지도 중심 이동
      map.panTo(latlng);

      // ################ road view ################
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      console.log(roadviewClient);
      // var position = new kakao.maps.LatLng(x, y);
      var position = new kakao.maps.LatLng(y, x);
      console.log("x: " + x + "y: " + y);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function(panoId) {
        // console.log("panoid" + panoId);
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });

      // ################ road view end ################

      // ################ near place start  ################

      // ################ near place end ################
    });
  }
  // 지도가 만들어졌을 때 중심좌표
  map.setBounds(bounds);
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
// function makeOverListener(map, marker, infowindow) {
//   return function () {
//     infowindow.open(map, marker);
//   };
// }

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
// function makeOutListener(infowindow) {
//   return function () {
//     infowindow.close();
//   };
// }

export default { load, makeMap, displayMap };
