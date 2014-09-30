(function(w) {
	var repub = {
		timeSelectCount : 1,
		addTimeSelect : function() {
			if (repub.timeSelectCount >= 5) {
				$("#addTimeSelectButton").tooltip('toggle');
			} else {
				repub.timeSelectCount++;
				$("#timeSelectParent").append($("#select_time").html());
			}
		},
		removeTimeSelect : function(btn) {
			$(btn).parent().remove();
			if (repub.timeSelectCount > 0) {
				repub.timeSelectCount--;
			}
		},
		submit0 : function(form) {
			$("#submit").text("保存中...");
			$("#alertMessageDiv").html('');
			$.ajax({
				type : $(form).attr('method'),
				url : $(form).attr('action'),
				data : $(form).serialize(),
				dataType : "json",
				success : repub.submit0Done,
				error : repub.httpError
			});
			return false;
		},
		submit0Done : function(data) {
			if (data.success == false) {
				$("#alertMessageDiv").html($("#alert_error").html());
				$("#alertMessageDiv > div > span").text(data.errorMsg);
			} else {
				$("#alertMessageDiv").html($("#alert_success").html());
				$("#alertMessageDiv > div > span").text("保存成功!");
				$("input[name='createOpt']").remove();// 创建成功后，移除createOpt，以免重复创建
			}
			$("#submit").text("保存修改");
		},
		switchOpen : function(switchOpen) {
			$("#alertMessageDiv").html('');
			var url = w.contextpath + '/safe/switch';
			var param = new Object();
			param.csrfToken = w.csrfToken;
			param.switchOpen = switchOpen;
			$.ajax({
				type : 'POST',
				url : url,
				data : param,
				dataType : "json",
				success : repub.switchOpenDone(switchOpen),
				error : repub.httpError
			});
			return false;
		},
		switchOpenDone : function(switchOpen) {
			return function(data) {
				if (data.success == false) {
					$("#alertMessageDiv").html($("#alert_error").html());
					$("#alertMessageDiv > div > span").text(data.errorMsg);
				} else {
					// $("#alertMessageDiv").html($("#alert_success").html());
					// $("#alertMessageDiv > div > span").text("保存成功!");
					if (!switchOpen) {
						$('#switchClose').show();
						$('#switchOpen').hide();
					} else {
						$('#switchOpen').show();
						$('#switchClose').hide();
					}
				}
			}
		},
		appError : function(title, errorMsg) {
			repub.alertError(title, errorMsg);
		},
		httpError : function(xhr, textStatus, errorThrown) {
			repub.alertError('抱歉啦，亲', '抱歉，网络发生错误了，请刷新页面试试...');
		},
		alertError : function(title, errorMsg) {
			// 显示错误消息
			$('#errorMsg > div[class="modal-header"] > h3').text(title);
			$('#errorMsg > div[class="modal-body"] > p').text(errorMsg);
			$('#errorMsg').modal('show');
		},
		endWith : function(s, endStr) {
			if (s == null || s == "" || s.length == 0
					|| endStr.length > s.length)
				return false;
			if (s.substring(s.length - endStr.length) == endStr)
				return true;
			else
				return false;
			return true;
		},
		startWith : function(s, preStr) {
			if (s == null || s == "" || s.length == 0
					|| preStr.length > s.length)
				return false;
			if (s.substr(0, preStr.length) == preStr)
				return true;
			else
				return false;
			return true;
		},
		"bookmarkPage" : function(url, title) {
			try {
				if (!url) {
					url = window.location
				}
				if (!title) {
					title = document.title
				}
				var browser = navigator.userAgent.toLowerCase();
				if (window.sidebar) { // Mozilla, Firefox, Netscape
					window.sidebar.addPanel(title, url, "");
				} else if (window.external) { // IE or chrome
					if (browser.indexOf('chrome') == -1) { // ie
						window.external.AddFavorite(url, title);
					} else { // chrome
						alert('请按 Ctrl和D 快捷键进行收藏');
					}
				} else if (window.opera && window.print) { // Opera -
					// automatically
					// adds to sidebar if
					// rel=sidebar in the tag
					return true;
				} else if (browser.indexOf('konqueror') != -1) { // Konqueror
					alert('请按 Ctrl和B 快捷键进行收藏');
				} else if (browser.indexOf('webkit') != -1) { // safari
					alert('请按 Ctrl和B 快捷键进行收藏');
				} else {
					alert('您的浏览器不支持该操作，请您点击浏览器的“收藏”菜单进行添加。');
				}
			} catch (err) {
				alert('您的浏览器不支持该操作，请您点击浏览器的“收藏”菜单进行添加。');
			}
		}
	};
	w.repub = repub;
}(window || this));
