// tempo restante para mostrar aviso sessão (segundos)
var tempoRestanteAviso = 60;

var countTimer;
var processTimer;
var timer_is_on = 0;
var redirectPage = "file:///C:/hfs-frameworks/sessaoExpirada.html";

var txtCountDown = null;
if (!txtCountDown) {
	txtCountDown = document.getElementById("dialog-countdown");
}

var maxTempoTotalSessao = "1800";
var txtCountDownGeral = null;
var processTimerGeral;
var tempoTotalSessao = maxTempoTotalSessao;

function timedCountGeral() {
	if (!txtCountDownGeral) {
		txtCountDownGeral = document.getElementById("dialog-countdown-geral");
	}
	
	txtCountDownGeral.innerHTML = converteSegundos(tempoTotalSessao);
	
	if (tempoTotalSessao < tempoRestanteAviso) {
		startIdleMonitor();
	}
	
	if (tempoTotalSessao == 0) {
		window.location.href = redirectPage;
		return;
	}
	
	tempoTotalSessao--;	
	processTimerGeral = setTimeout("timedCountGeral()", 1000);
}

function startIdleMonitor() {
	
	if (!txtCountDown) {
		txtCountDown = document.getElementById("dialog-countdown");
	}
	
	countTimer = tempoTotalSessao;
	txtCountDown.innerHTML = countTimer;
	$("#timeoutSessionDialog").modal("show");
}

function timedCount() {

	if (!txtCountDown) {
		txtCountDown = document.getElementById("dialog-countdown");
	}
		
	txtCountDown.innerHTML = countTimer;
	
	if (countTimer == 0) {
		window.location.href = redirectPage;
		return;
	}
	
	countTimer--;
	processTimer = setTimeout("timedCount()", 1000);
}

function doTimer() {
	if (!timer_is_on) {
		timer_is_on = 1;
		timedCount();
	}
}

function stopCount() {
	clearTimeout(processTimer);
	clearTimeout(processTimerGeral);
	timer_is_on = 0;
	$("#timeoutSessionDialog").modal("hide");
	tempoTotalSessao = maxTempoTotalSessao;
	timedCountGeral();
	keepAlive();
}

function converteSegundos(tempo) {
	var minutes = Math.floor(tempo / 60);
	var seconds = tempo - minutes * 60;
	return minutes + "min" + seconds + "s";
}
