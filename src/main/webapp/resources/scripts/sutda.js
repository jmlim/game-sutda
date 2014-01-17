/**
 * 섯다 엔진에 대한 클래스
 */

/*
 * Simple JavaScript Inheritance By John Resig http://ejohn.org/ MIT Licensed.
 */
// Inspired by base2 and Prototype
(function() {
	var initializing = false, fnTest = /xyz/.test(function() {
		xyz;
	}) ? /\b_super\b/ : /.*/;

	// The base Class implementation (does nothing)
	this.Class = function() {
	};

	// Create a new Class that inherits from this class
	Class.extend = function(prop) {
		var _super = this.prototype;

		// Instantiate a base class (but only create the instance,
		// don't run the init constructor)
		initializing = true;
		var prototype = new this();
		initializing = false;

		// Copy the properties over onto the new prototype
		for ( var name in prop) {
			// Check if we're overwriting an existing function
			prototype[name] = typeof prop[name] == "function"
					&& typeof _super[name] == "function"
					&& fnTest.test(prop[name]) ? (function(name, fn) {
				return function() {
					var tmp = this._super;

					// Add a new ._super() method that is the same method
					// but on the super-class
					this._super = _super[name];

					// The method only need to be bound temporarily, so we
					// remove it when we're done executing
					var ret = fn.apply(this, arguments);
					this._super = tmp;

					return ret;
				};
			})(name, prop[name]) : prop[name];
		}

		// The dummy class constructor
		function Class() {
			// All construction is actually done in the init method
			if (!initializing && this.init)
				this.init.apply(this, arguments);
		}

		// Populate our constructed prototype object
		Class.prototype = prototype;

		// Enforce the constructor to be what we expect
		Class.prototype.constructor = Class;

		// And make this class extendable
		Class.extend = arguments.callee;

		return Class;
	};
})();

var Sutda = {};
Sutda.ViewRenderer = Class.extend({
	init: function(){
		 this.playerPositionTop = [ 600, 325, 50, 325 ];
		 this.playerPositionLeft = [ 450, 50, 450, 875 ];
	 },
	 gameStart: function(gameContext) {
		var players = gameContext.room.players;
		var top = this.playerPositionTop;
		var left = this.playerPositionLeft;

		var centerPosition = $("#card-stack").position();

		$(".player").each(function(e) {
			$(this).children().remove();
		});
		
		$(".batting").show();

		$.each(players, function(index, player) {
			var $player = $("#player" + player.seq);
			$player.append("<span class='player-name'/>");

			var $playerName = $player.find(".player-name");
			var playerNameCss = {
				"position" : "absolute",
				"font-size" : "20px",
				"font-weight" : "bold",
				"color" : "yellow"
			};
			$playerName.css(playerNameCss);
			$playerName.text(player.name);

			var moveLeft = left[index] - centerPosition.left;
			var moveTop = top[index] - centerPosition.top;

			moveTop += 75;
			$playerName.animate({
				left : moveLeft,
				top : moveTop
			});

			$player.append("<span class='player-money'/>");

			var $playerMoney = $player.find(".player-money");
			var playerMoneyCss = {
				"position" : "absolute",
				"font-size" : "20px",
				"font-weight" : "bold",
				"color" : "skyblue"
			};

			$playerMoney.css(playerMoneyCss);
			$playerMoney.text(player.money);

			moveTop -= 100;
			$playerMoney.animate({
				left : moveLeft,
				top : moveTop
			});
		});
		
		this.distributeCard(players);
	},
	gameEnd: function(gameContext) {
		var $menuBar = $(".menu-bar");
		$menuBar.find(".game-start").show();
		$menuBar.find(".batting").hide();
	},
	reRound: function(gameContext) {
		$(".player").each(function(e) {
			$(this).children().remove();
		});

		this.gameStart(gameContext);
	},
	distributeCard: function(players) {
		var time = 200;
		var top = this.playerPositionTop;
		var left = this.playerPositionLeft;

		var centerPosition = $("#card-stack").position();

		$.each(players, function(index, player) {
			var cards = player.cards;
			window.setTimeout(function() {
				$.each(cards, function(cardIndex, card) {

					var cardNum = card.cardNum;
					//세션에 담긴 플레이어 아이디와 게임에 참여하고 있는 플레이어의 아이디가 일치할 경우 카드를 보여줄 예정 지금은 임시.
					if(player.seq == 1) {
						cardNum = cardNum < 10 ? 0 + "" + cardNum : cardNum;
					} else {
						cardNum = 99;						
					}
					var $player = $("#player" + player.seq);
					

					var $existingCard = $player.find(".card");

					var cardCreate = true;
					if ($existingCard.size() >= 1) {
						if (cardIndex == 0) {
							cardCreate = false;
						}
					}

					if (cardCreate) {
						$player.append("<div class='card-warp' style='position: absolute'/>");
						var $cardWarp = $player.find(".card-warp")[cardIndex];
						$cardWarp = $($cardWarp);

						var moveLeft = left[index] - centerPosition.left;
						var moveTop =  top[index] - centerPosition.top;

						if(cardIndex > 0) {
							moveLeft += 30;
						}
						$cardWarp.append("<img class='card' src='" + contextPath
								+ "/resources/images/" + cardNum + ".png'/>");

						$cardWarp.animate({
							left : moveLeft,
							top : moveTop
						}, 1000);
					}
				});
			}, time);

			time += 200;
		});
	},
	batting: function(gameContext, action) {
		console.log(gameContext);
		var player = gameContext.currentPlayer;
		var currentSeq = gameContext.currentSeq;
		var battingMoney = gameContext.callStakes;
		var throwNumber = 1;
		if (battingMoney) {
			throwNumber = Math.ceil(battingMoney / 100);
		}

		$player = $("#player" + currentSeq);

		var tag;
		var cssObj;
		var attrObj;
		if (action == "check") {
			tag = "<span class ='throw-image'/>";
			cssObj = {
				"position" : "absolute",
				"color" : "yellow",
				"font-size" : "30px",
				"font-weight" : "30px"
			};
			attrObj = {};
		} else if (action == "die") {
			tag = "<span class ='throw-image'/>";
			cssObj = {
				"position" : "absolute",
				"color" : "red",
				"font-size" : "30px",
				"font-weight" : "30px"
			};
			attrObj = {};
		} else {
			tag = "<img class ='throw-image'/>";
			cssObj = {
				"position" : "absolute"
			};
			attrObj = {
				"width" : "50px",
				"height" : "50px",
				"src" : contextPath + "/resources/images/coin.png"
			};
		}

		$player.find(".player-money").text(player.money);
		for (var i = 0; i < throwNumber; i += 1) {
			var $cardWarp = $player.find(".card-warp")[0];

			$cardWarp = $($cardWarp);
			$cardWarp.append(tag);
			var $throwImage = $player.find(".throw-image");
			$throwImage.attr(attrObj);
			$throwImage.css(cssObj);
			if (action) {
				$throwImage.text(action);
			}

			var randLeftNum = Math.floor((Math.random() * 155) + 1);
			var randTopNum = Math.floor((Math.random() * 172) + 1);
			var negativeCheck = Math.floor((Math.random() * 2) + 1);

			if (negativeCheck == 1) {
				randLeftNum = randLeftNum * -1;
			} else {
				randTopNum = randTopNum * -1;
			}
			var cardWarpPosition = $cardWarp.position();

			$throwImage.animate({
				left : (cardWarpPosition.left * -1) + randLeftNum,
				top : (cardWarpPosition.top * -1) + randTopNum
			}, 1000);
			$throwImage.removeClass("throw-image");
			$throwImage.addClass("thrown-image");
		}
	},
	checkWinner: function(gameContext) {
		var returnObj = {
			winnerPlayer: gameContext.winnerPlayer,
			gameStatus: gameContext.gameStatus,
			winnerCardType: gameContext.winnerCardType
		};

		return returnObj;
	},
	receivedStakes: function(gameContext) {
		var players = gameContext.room.players;
		var winnerPlayer = gameContext.winnerPlayer;
		var $winnerPlayerCardWarp;
		var winnerPlayerCardWarpPosition = {};
		var self = this;

		$winnerPlayerCardWarp = $("#player" + winnerPlayer.seq).find(
				".card-warp");
		winnerPlayerCardWarpPosition = $winnerPlayerCardWarp
				.position();

		$.each(players, function(index, player) {
			var $player = $("#player" + player.seq);
			var $cardWarp = $player.find(".card-warp");
			var $thrownImage = $player.find(".thrown-image");

			var cards = player.cards;
			$.each(cards, function(cardIndex, card) {
				var $cardWarp = $($player.find(".card-warp")[cardIndex]);
				var cardNum = card.cardNum;
				cardNum = cardNum < 10 ? 0 + "" + cardNum : cardNum;
				$cardWarp.find('.card').attr(
						"src",
						contextPath + "/resources/images/" + cardNum
								+ ".png");
			});
			
			var cardWarpPosition = $cardWarp.position();
			var coinMoveLeft = winnerPlayerCardWarpPosition.left
					- cardWarpPosition.left;
			var coinMoveTop = winnerPlayerCardWarpPosition.top
					- cardWarpPosition.top;

			$thrownImage.animate({
				left : coinMoveLeft,
				top : coinMoveTop
			}, 2000, self.removeBattingCoin);

			$player.find(".player-money").text(player.money);
		});
	},

	removeBattingCoin: function() {
		window.setTimeout(function() {
			$(".thrown-image").remove();			
		},2000);
	}
});

Sutda.gameService = Class.extend({
	init: function() {
		var self = this;
		this.viewRenderer = new Sutda.ViewRenderer();
		this.gameButton = $(".menu-bar button");
		
		this.gameButton.click(function(event) {
			event.preventDefault();
			var $target = $(event.target);
			var titleName = $target.attr("title");

			if(titleName === "game-start") {
				self.gameStart(event);
			} else {
				self.batting(event);
			}
		});

		$(".batting").hide();
	},
	ajaxRequest: function(url, type, callback) {
		$.ajax({
			url : url,
			type : type,
			// data: params,
			async : false,
			dataType : 'json',
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			error : function(request, textStatus, errorThrown) {
			},
			success : function(data) {
				callback(data);
			}
		});
	},
	gameStart: function(event) {
		var $target = $(event.target);
		var self = this;
		var url =[];
		url.push(contextPath);
		url.push("sutda");	
		url.push("game-start");
		url = url.join("/");
		var callback = function(gameContext) {
			$target.css("display", "none");
			self.viewRenderer.gameStart(gameContext);
		};

		this.ajaxRequest(url, 'POST', callback);
	},
	batting: function(event) {
		var $target = $(event.target);
		var self = this;

		var url = [];
		var action = $target.attr("title");
		url.push(contextPath);
		url.push("sutda");
		url.push("batting");
		url.push(action);
		url = url.join("/");

		var callback = function(data) {
			self.viewRenderer.batting(data, action);
			var gameStatus = data.gameStatus;
			if(gameStatus === 'DISTRIBUTE') {
				self.distributeCard(data);
			}
			if(gameStatus === 'STOP') {
				self.checkWinner(data);
			}
		};
		this.ajaxRequest(url, 'POST', callback);
	},
	distributeCard: function() {
		var self = this;
		var url =[];

		url.push(contextPath);
		url.push("sutda");	
		url.push("distribute-card");
		url = url.join("/");

		var callback = function(data) {
			var players = data.room.players;
			self.viewRenderer.distributeCard(players);
		};
		this.ajaxRequest(url, 'POST', callback);
	},
	checkWinner: function() {
		var self = this;
		var url =[];
		url.push(contextPath);
		url.push("sutda");	
		url.push("check-winner");
		url = url.join("/");

		var callback = function(data) {
			var returnObj = self.viewRenderer.checkWinner(data);
			if(returnObj.gameStatus === 'STOP') {
				self.receivedStakes(data);
			} else if(returnObj.gameStatus == 'REROUND') {
				self.reRound(data);
			}
		};
		self.ajaxRequest(url, 'POST', callback);
	},
	receivedStakes: function() {
		var self = this;
		var url =[];
		url.push(contextPath);
		url.push("sutda");	
		url.push("received-stakes");
		url = url.join("/");

		var callback = function(data) {
			if(data.gameStatus === 'STOP') {
				self.viewRenderer.receivedStakes(data);
				self.gameEnd();
			}
		};

		this.ajaxRequest(url, 'POST', callback);
	},
	reRound: function() {
		var self = this;
		var url =[];
		url.push(contextPath);
		url.push("sutda");	
		url.push("re-round");
		url = url.join("/");

		var callback = function(data) {
			self.viewRenderer.reRound(data);
		};

		this.ajaxRequest(url, 'POST', callback);
	},
	gameEnd: function() {
		var self = this;
		var url =[];
		url.push(contextPath);
		url.push("sutda");	
		url.push("game-end");
		url = url.join("/");

		var callback = function(data) {
			self.viewRenderer.gameEnd(data);
		};

		this.ajaxRequest(url, 'POST', callback);
	}
});
