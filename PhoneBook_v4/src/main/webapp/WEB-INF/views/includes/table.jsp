
<%@page import="com.phonebook.vo.PhoneBookVO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 요청 객체에서 list 속성 받아오기
List<PhoneBookVO> list = (List<PhoneBookVO>) request.getAttribute("list");
%>


<form id="search-form" action="<%=request.getContextPath()%>/?a=search"
	method="POST" class="float-left">
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<label class="input-group-text" id="inputGroup-sizing-default"
				for="keyword">검색어</label>
		</div>
		<input class="inputText form-control"
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default" type="text"
			name="keyword" id="keyword" /> <input type="submit"
			class="btn btn-outline-secondary btn-sm" value="검색" id="search" />
	</div>
</form>


<table class="table table-striped">
	<thead class="table-dark">
		<tr>
			<th>이름</th>
			<th>휴대전화</th>
			<th>전화번호</th>
			<th>도구</th>
		</tr>
	</thead>
	<tbody>
		<!-- 전화번호 리스트: 목록 -->
		<!-- 루프 시작 -->
		<%for (PhoneBookVO vo : list) {%>
		<tr>
			<td><%=vo.getName()%></td>
			<td><%=vo.getHp()%></td>
			<td><%=vo.getTel()%></td>
			<td colspan="2">
				<!-- 삭제 폼 -->
				<form action="<%=request.getContextPath()%>/phonebook?a=delete"
					method="POST">
					<input type="hidden" name="id" value="<%=vo.getId()%>" /> <input
						type="submit" value="삭제" class="btn btn-outline-secondary btn-sm" />
				</form>
			</td>
		</tr>
		<%
		}
		%>
		<!-- 루프의 끝 -->
	</tbody>
</table>
<div class="clearfix">
	<button type="button"
		class="btn btn-outline-secondary btn-sm float-left"
		data-toggle="modal" data-target="#insertModal">새 주소 추가</button>
</div>


<!-- 새주소 추가 모달 시작 -->
<div class="modal" tabindex="-1" id="insertModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="container-sm ">

				<button type="button" class="close float-right" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

				<!-- 정적 페이지 인클루드 -->
				<%@ include file="/WEB-INF/views/includes/header.jsp"%>

				<h4 class="modal-title mb-2">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
						fill="currentColor" class="bi bi-plus-circle mb-2" viewBox="0 0 16 16">
  <path
							d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  <path
							d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
</svg>
					새 주소 등록
				</h4>


				<form method="POST" action="<%=request.getContextPath()%>/"
					id="insertModal">
					<input type="hidden" name="a" value="insert">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" id="inputGroup-sizing-default"
								for="name">이름</label>
						</div>
						<input class="inputText form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" type="text"
							name="name" id="name" />
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" id="inputGroup-sizing-default"
								for="hp">휴대전화</label>
						</div>
						<input class="inputText form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" type="text"
							name="hp" id="hp" />
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" id="inputGroup-sizing-default"
								for="tel">집전화</label>
						</div>
						<input class="inputText form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" type="text"
							name="tel" id="tel" />
					</div>
					<div class="clearfix">
						<input type="submit" value="주소 등록"
							class="btn btn-outline-secondary btn-sm float-right" />
				</form>
				<button id="showList"
					class="btn btn-outline-secondary btn-sm float-right">
					<a href="<%=request.getContextPath()%>/?a=list">목록 보기</a>
				</button>
			</div>
		</div>



	</div>

</div>
</div>
</div>


<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    -->
</body>
</html>