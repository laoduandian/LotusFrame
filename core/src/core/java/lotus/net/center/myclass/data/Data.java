package lotus.net.center.myclass.data;

import com.badlogic.gdx.graphics.Color;

public class Data {
	public static final Color color = new Color(76f/255, 186f/255, 1, 1);
	public static final Color COLOR_MISS = new Color(236f/255, 89f/255, 89f/255, 1);
	public static final Color COLOR_BLACK = Color.BLACK;
	public static final Color COLOR_START = new Color(54f/255, 159f/255, 198f/255, 1);
	public static final Color COLOR_WIHTE = new Color(0,0,0,0);
	public static final Color COLOR_GET = new Color(7f/255, 96f/255, 118f/255, 0.5f);
	public static final Color COLOR_TOUCH = new Color(7f/255, 96f/255, 118f/255, 1);
	public static final Color COLOR_WRONG = new Color(236f/255, 94f/255, 94f/255, 1);
	public static final Color COLOR_BEST = new Color(0, 201f/255, 222f/255, 1);
	public static final String BUTTON_MUSIC_STRING[] = {"c2",
		"c2",
		"g2",
		"g2",
		"a2",
		"a2",
		"g2",
		"f2",
		"f2",
		"e2",
		"e2",
		"d2",
		"d2",
		"c2"};
	public static int button_music_id = 0;
	public static final Color COLOR_CHOOSE = new Color(1, 144f/255, 0, 1);
	public static final Color COLOR_NOTCHOOSE = new Color(37f/255, 163f/255, 222f/255, 1);
	public static final Color COLOR_LOCK = new Color(156f/255, 173f/255, 179f/255, 1);
	public static Color seColor[]={
			new Color(6f/255, 124f/255, 255f/255, 1),
			new Color(255f/255, 112f/255, 0f/255, 1),
			new Color(0f/255, 203f/255, 31f/255, 1),
			new Color(255f/255, 41f/255, 77f/255, 1),
			new Color(0f/255, 187f/255, 164f/255, 1),
			new Color(212f/255, 10f/255, 236f/255, 1)
		};
	public static final Color BG_COLOR[]={
			new Color(124/255f,124/255f,124/255f,1),
			new Color(99/255f,117/255f,129/255f,1),
			new Color(90/255f,99/255f,138/255f,1),
			new Color(125/255f,93/255f,192/255f,1),
			new Color(97/255f,93/255f,192/255f,1),
			new Color(33/255f,149/255f,192/255f,1),
			new Color(0,143/255f,214/255f,1),
			new Color(0,184/255f,169/255f,1),
			new Color(61/255f,162/255f,118/255f,1),
			new Color(61/255f,162/255f,66/255f,1),
			new Color(122/255f,177/255f,26/255f,1),
			new Color(116/255f,128/255f,99/255f,1),
			new Color(128/255f,106/255f,99/255f,1),
			new Color(142/255f,103/255f,84/255f,1),
			new Color(163/255f,88/255f,88/255f,1),
			new Color(196/255f,62/255f,100/255f,1),
			new Color(225/255f,7/255f,124/255f,1),
			new Color(227/255f,52/255f,52/255f,1),
			new Color(246/255f,95/255f,42/255f,1),
			new Color(225/255f,130/255f,7/255f,1)
		};
	public static int data_brick[][] = {
			
	};
	public static String name[][]={
			{"loqsonqsstuvtsqpoooqpooopqpompqploqsonqsstuvtsqpoooqpooopqpmnopoqsqposnmnmnmlqsqposnmnooopqploqsonqsstuvtsqpoooqpooopqpmnopo"},//月亮代表我的心
			{"qqqqponpppolqpooolqpoopqqqqponpppolqpooolqpooppqrrrrrqpooopplsssrqpppqqmrqrqpoonolqrqrqpopslqrsrqslqrsrqslqrsrqopppqossoonoossoonoorrqqpporrqqppolqrsrqslqrsrqslqrsrqopppqossoonoossoonoorrqqppoorqpomoo"},//安静
			{"tvutstqtstvuvutuvvwxxxxwvutustvutstqstxwvuvvuttttsstqsstxwvuvvvuttsut"},//心语心愿
			{"lonollonollonooommllonollqppolonommmotspprrqqqqnpoonoonorlsrqppprrqqqqvutuvvpotttssslsrqqrqqrqrqpooqstttspprqoqstttspprqrqpopqmmoono"},//童话
			{"jklllllkjiilnnonooopqnnlhmmmjmolllljhkkkjklmmmomljkjjklllljihhonmlmljhhmmlllkjoooonopplrqllrplrqrqrqpoonmmqmqmllqlqlrqrqoplrqllrsplrqqqrqpqonmmqmqmllqlqpomopnmnmnqpoo"},//好好恋爱
			{"opqqqrqppoppsponoooqqoomnnqnmlmmmrrqsomlmmmrroompoopqqqrqppoppsponoooqqoomnnqnmlmmrrqsomlmmmrrooono"},//痴心绝对
			{"stvtxxtwwwvuvwxwsvvutuvrrrvvwvssssyxwxxstvtxxtwwwvuvwxwsvvvutuvrrrvvwvsssstuvvuvv"},//会呼吸的痛
			{"jjkllkjihhijjiijjkllkjihhijihhiijhijkjhijkjihiejjkllkjihhijihh"},//欢乐颂
			{"oossttsrrqqppossrrqqpssrrqqpoossttsrrqqppo"},//小星星
			{"hhijlmonnnljiiijkmoqppnlhhijlmonnnljiiijkmoqppnlmlmlmnnmnppppnopqqqpppoqnlmlmopqqpqqpopommlmopqqpqqstsqqqqppopomqqrqpopqqqppopomoo"},//千千阙歌
			{"jjijjljijhhijljiihijlmlmlljlejijljiiihijjijjljijhhijljiihijlmlmlljljijljiihhijjlmmqpoomlmljihfhiihihijjlmmqpoopolljnohijih"},//菊花台
			{"qsqpqpqqpmoqppomopqspqtsspopomopqspqtssqpqpostqmqppoqsvsttsqqssqstvwvsqpsqqqsvstvwvsqsvtqpqsxwwv"},//北京欢迎你
			{"hkkkhljkhknnmlkjklhkkkhljkhkmomkilmknmliijkllnmliijkllhkkkhljkhkmomkilmkilmkk"},//婚礼进行曲
			{"hijjjjijljjlmmmnljjihhhhfjlhiiihljihijjjjijljjlmmmmnljlllmoomkillmoomoplllqqqqqpponooopjlllmoooopmoooppppoqpllllqqqsqpponooopjlllmoooopmooopppoqpo"},//梦醒时分
			{"lljijfijljilljijeijlihhijlmljljjiihihihiijljjlljijfijljilljijeijlihhijlmljljjiiejiih"},//青花瓷
			{"mnoooonqqtttsrsoorrrsqnqpponoonoooonqqtttsrsoorrrqpnom"},//夜曲
			{"jkjghjmonljjkjghjmonlqmmmoqlkmppqrpqrqmpqrpqrmmqrsqrsmpqrmrskpqpqrqjkjghjmonljjkjghjmonlm"},//暗香
			{"ljlijihhgfghghijljlponohgfghghihlmnononmlmlhijkjklhijlmnononpopoqlhijkjkjihghlmnonoponmlhijkjklhhmllmnonopopqlhijkjklhhmllmnonopopqlhijijilhijhlmnonomnlkljjjkljeeiiijkieehhhijighgfghgfefmlikjihhgfiihffgh"},//遇见
			{"lopqpoqplljmnonmonjlmllkkojjpopqrqqpomplopqpoqpnlqmnonmonjlmllkkopqomnomotssposqqponomjmopotrrqrmnopqrssstosnpopqmorqrspposqqponomjmopotrrqrmnopqrssstosnpopqrmrqopo"},//约定
			{"mqnmopoqmtstsprqmtspqrqomqpnmmqnmopoqmtstsprqmtspqrqpomponmmnopqonlmopqqmnopqonlmopoomponmmnopqonlmopqqmnopqonlmopoooopqonlonmnt"},//美丽的神话
			{"mljlomlmjlmljihfljiijllmjihljihfhe"},//世上只有妈妈好
			{"opqrsssrqrrrqpoqsopqrsssrqrrrqpoqotttsrsssrqrrrqpoqstttsrsssrqrrrqpoqo"},//洋娃娃和小熊跳舞
			{"cefhifhelomljliijgfefhichfefhejlgifhececefgifefhiljijihfechfhfecefhe"},//梁祝
			{"opqssssqsvvvvtqsooooopqssstqpppqqopopqssssqsvvvvtqsooooopqssstqpppomlo"},//忘情水
			{"qqstttqqstttsqstqstqqsttttsqpqspqqqstttqqstttsqsvusqtqqpopqpopspqm"},//等一分钟
			{"onopoljkkkljonopollmmmononopqoljopoljonopqolorqpoqqrstoopqppqrssrqpqqrstssuuvuspqrqrsqrstoopqppqsquuqvvuvtsootsrqrstsqrstoopqppqrssrqpqqrstssuuvuspqrqrsqrstoopqppqsquuqvvuvtsootsrqrs"},//想唱就唱
			{"lllkkmlkkkkhkkkkooppopppqqplpppqpolmooopqpppqpolmooopqplpppqpolmooopqpppopppomoomoo"},//千里之外
			{"ijjijljiijjijmljihfhiifhifhijiijjijljiijjeijmljihfhiifhiihijihfhfhfhihfhfhfhjhhjjjmljjiieijjijlmlhijmljllmomljljhijhfjieijjijlmlhijmljllmomljljhfjihhfhihh"},//发如雪
			{"ihijiihihffiihijiihihffellljjjklkkiijhhfhmmmllihijhhihhfiihijiihijljllllmljklkkiihjihfihihlllmnmljjjiillljiihihijjhhijijljihifhhijijlmlljjjiilllmnmljjjiillljiihihijjhhijijlmlljlmmljljiihijihfh"},//东风破
			{"mmnnoonnmmjjhhffllkkjklkkkllmmnnlliikkjjiikjjfhjijfhjijfhkjkfhkjkkjkkllmlmjqmoqpqmoqpqmorqrmorqrrqrrsststqojjkkiinniijjhhmlmhhiigjijooopponmllmljooopponmllmlm"},//梦中的婚礼
			{"jmmmmmmmmjklkjlllllllmnnjjmmmmmmmmqqpnnnnnjononjmmmmmmmmjklkilllllllmnnjjmmmmmmmmqqpnnnnnnnmnmqtqqqqomjjqpjssssssstuuqqtqqqqomjjqppppppjnnm"},//有没有人告诉你
			{"fehhhhefhhihfhiiiihfiijijlmmlmljijihfefhhhhihjimmmmomllmljihhhhijjihhhhomllmljlmoomljimmmmomllmljihhhhljjihhhhijlljljlmfjihh"},//大海
			{"qpopqsssststuvvvvvvutsttsssqpoqrqppqppqpppoooopootuvvvvvvutsssqpovvvvwwwvwxxwvvvvvwwssxwvvvvvwwwwvuvttutuvvvwvwxxwxv"},//海闊天空
			{"lljihijiihfhffelljihijiihfhfijikjihhhhihonmmljihhhhihijhhhihonmmloomjhijjieljiheljihhonnmlmljlhonnmllojihjkjhfhfeeljiheljijihhonnmlmljlhonnmllojihjkjhfhkjhfhih"},//我愿意
			{"momomlmmomolmpqopsqpqopqopmopmolmopomlmolmomlmopomlmolmpqspqtqtsrqsqpqqstutsqqstustututsqstutsqspqpopsrqsppqstqsqpompomopqnnqpnmmomlmmomlmqspqt"},//男儿当自强
			{"jlmlmnljllllkkkjihjkkkjihjhihjijihijlmlmnljllllkkkjihjkkkjihjlopqpoqopomqpopqqpooolopqnnnlnopqqrqopqpooolopqnnnlnopqqmlrqolqpoooonoljmqpoooonoljm"},//唯一
			{"mnoklolhijkjihjhkloklolhijkonossonoqonolqpmnoponosstuvsqrqmpoovutqplutspopqrrtsqpqqrsovutruvwswuvtuvtuvws"},//知足
			{"qpqpqpomoppqpomolqpqpqpomoppqpomopqpqpqpomoppqpomolqssstsqpqppqpomoosstvuutqpqqsttmqpqssqsvutuqttutsqplmo"},//两只蝴蝶
			{"ssssqopqoomopqomlssssqopqoomopqompmooopqqpqrqpoomqmoooppqqpqrqpoomssqstsopqovwvtqststvtsqqqpqsqpoqpsqstsopqovwvtqststvtsqqpqsqpomo"},//城里的月光
			{"ehijkjhihfhehijjklhkjlijijlonmmllmnmljkklmlkjiggfefhomklmnnnnmljomklmfffmkjiehijkjhhiiiihffggfemliikkkjih"},//同一首歌
			{"ehhjmjllmljkjifiiknnmlkkjfghiehhjmjllmljkjifiiknnmlkkjfgihmmlklmlijkilmlkinmlmlklmjih"},//哆啦A梦
			{"hjloeilnfhjmcgildfhkaehjdfhkegilhqjloepgilofhjmncgjlmdfhklacehmdfhknegilonohgblijchonmcnqstdrqprqapondmlkjeikjiehijkegilkfjmlkclkjidhfmnaonmldkjimlmlsjqrsqrselmnopqrfqopqjkglmlkljklfkmlkjijeihijklmfkmlmnoglmnopqrssiqrsqrselmnopqrfqopqjkglmlkljklfkmlkjiejihijklmfkmlmnoglmnopqrshqjloepgilofhjmncgjlmdfhklacehmdfhkne"},//卡农
			{"lllllllorqqppnoplllmnmnokjknopmlmlmnnhmnonmnlmlklkjmnonmnlppqqrqppmnonmnlmlknjmnonmnlppqqqqrqplmnooooooonoppppppppppoloooololooooololooooopqqqqqqrqpopqqqqqqqrqpnopppppppqpoooosrrqqppoppopoporqqppoopqrqsoqqqqqqqrqpnosssssspqpommosrrqqppoqplqppomnolmnklmkjkllmnmmnonmnlmlklkjmnonmnlppqqqpqpmnonmnmlmlkojmnonmnlppqqqrqplmnooooonopppppppnlooololoooololoooopqqqqqrqqpopqqqqqqqrqpnopppppppqpoooosrrqqppoppopoporqqppoopqrqsoqqqqqqqrqpnosssssspqpommosrrqqppoqplqppoopoqqrqpnoppnolooloolooopqrqrsoqqqqqqqrqpnosssssspqpommosrrqqppoqplqppojloprqpopqkljloprqpopqsl"},//说好的幸福呢
			{"ehhhegggehhheiiiehhhegggehhheiiiekkkkkjjkkkljihgghelllllkklllllkkjjiekkkkkjjkkkljihggihfhlmhlllllkkjjihonoollllkkjhonoollllppohononmhnmnmlhmlmlkkjklmmlhonoollllkkjhonoollllppohononmhnmnmlhmlmlkkjklmmlhlhkjhhlhkjh"}//不能说的秘密
	};
	public static Color springColors[] = {
	new Color(200f/255, 210f/255, 0,       1),
	new Color(196f/255, 188f/255, 1f/255,  1),
	new Color(157f/255, 162f/255, 0,       1),
	new Color(250f/255, 196f/255, 0,       1),
	new Color(242f/255, 151f/255, 0,       1),
	new Color(237f/255, 113f/255, 1f/255,       1),//第一排
	new Color(1,        62f/255,  17f/255, 1),
	new Color(177f/255, 80f/255,  1f/255,  1),
	new Color(95f/255,  42f/255,  0,       1),
	new Color(119f/255, 43f/255,  30f/255, 1),
	new Color(92f/255,  18f/255,  31f/255, 1),
	new Color(229f/255, 30f/255,  33f/255, 1),//第二排
	new Color(179f/255, 26f/255,  54f/255, 1),
	new Color(229f/255, 40f/255,  120f/255,1),
	new Color(229f/255, 0,        127f/255,1),
	new Color(141f/255, 28f/255,  134f/255,1),
	new Color(83f/255,  1f/255,   86f/255, 1),
	new Color(69f/255,  22f/255,  124f/255,1),//第三排
	new Color(36f/255,  4f/255,   87f/255, 1),
	new Color(134f/255, 194f/255, 47f/255, 1),
	new Color(80f/255,  174f/255, 52f/255, 1),
	new Color(0,        159f/255, 57f/255, 1),
	new Color(0,        128f/255, 93f/255, 1),
	new Color(72f/255,  94f/255,  108f/255,1),//第四排
	new Color(1f/255,   177f/255, 174f/255,1),
	new Color(0,        147f/255, 167f/255,1),
	new Color(0,        124f/255, 186f/255,1),
	new Color(1f/255,   66f/255,  146f/255,1),
	new Color(59f/255,  75f/255,  153f/255,1),
	new Color(107f/255, 68f/255,  1f/255, 1),//第五排
	new Color(77f/255,  76f/255,  1,      1),
	new Color(77f/255,  26f/255,  1,      1),
	new Color(51f/255,  0,        1,      1),
	new Color(51f/255,  66f/255, 11f/255, 1),
	new Color(27f/255,  27f/255, 27f/255, 1),
	new Color(0, 0, 0, 1)//第六排
	};
	public static Color colors[] = {
			new Color(200f/255, 210f/255, 0,       1),
			new Color(196f/255, 188f/255, 1f/255,  1),
			new Color(157f/255, 162f/255, 0,       1),
			new Color(250f/255, 196f/255, 0,       1),
			new Color(242f/255, 151f/255, 0,       1),
			new Color(237f/255, 113f/255, 1f/255,       1),//第一排
			new Color(1,        62f/255,  17f/255, 1),
			new Color(177f/255, 80f/255,  1f/255,  1),
			new Color(95f/255,  42f/255,  0,       1),
			new Color(119f/255, 43f/255,  30f/255, 1),
			new Color(92f/255,  18f/255,  31f/255, 1),
			new Color(229f/255, 30f/255,  33f/255, 1),//第二排
			new Color(179f/255, 26f/255,  54f/255, 1),
			new Color(229f/255, 40f/255,  120f/255,1),
			new Color(229f/255, 0,        127f/255,1),
			new Color(141f/255, 28f/255,  134f/255,1),
			new Color(83f/255,  1f/255,   86f/255, 1),
			new Color(69f/255,  22f/255,  124f/255,1),//第三排
			new Color(36f/255,  4f/255,   87f/255, 1),
			new Color(134f/255, 194f/255, 47f/255, 1),
			new Color(80f/255,  174f/255, 52f/255, 1),
			new Color(0,        159f/255, 57f/255, 1),
			new Color(0,        128f/255, 93f/255, 1),
			new Color(72f/255,  94f/255,  108f/255,1),//第四排
			new Color(1f/255,   177f/255, 174f/255,1),
			new Color(0,        147f/255, 167f/255,1),
			new Color(0,        124f/255, 186f/255,1),
			new Color(1f/255,   66f/255,  146f/255,1),
			new Color(59f/255,  75f/255,  153f/255,1),
			new Color(107f/255, 68f/255,  1f/255, 1),//第五排
			new Color(77f/255,  76f/255,  1,      1),
			new Color(77f/255,  26f/255,  1,      1),
			new Color(51f/255,  0,        1,      1),
			new Color(51f/255,  66f/255, 11f/255, 1),
			new Color(27f/255,  27f/255, 27f/255, 1),
			new Color(0, 0, 0, 1)//第六排
			};
	public static Color red = new Color(252f/255, 62f/255, 57f/255, 1);
	public static Color hui = new Color(201f/255, 201f/255, 201f/255, 1);
	public static Color moreColor[]={
			new Color(151f/255, 199f/255, 56f/255, 1),
			new Color(255f/255, 214f/255, 66f/255, 1),
			new Color(61f/255, 206f/255, 123f/255, 1)
		};
	public static Color doubleColor[] = {
			new Color(150f/255, 188f/255, 103f/255, 1),
			new Color(144f/255, 114f/255, 78f/255, 1),
			new Color(255/255, 41f/255, 77f/255, 1),
			new Color(6f/255, 152f/255, 255/255, 1),
		};
	public static Color bgColor[]={
			new Color(83f/255, 215f/255, 105f/255, 1),
			new Color(140f/255, 107f/255, 176f/255, 1),
			new Color(149f/255, 142f/255, 124f/255, 1),
			new Color(92f/255, 146f/255, 181f/255, 1),
			new Color(144f/255, 114f/255, 78f/255, 1),
			new Color(38f/255, 147f/255, 140f/255, 1)
		};
	public static final char sounds[] = {
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
		};
}
