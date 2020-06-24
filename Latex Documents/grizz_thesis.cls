\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{grizz_thesis}
\LoadClass[12pt, oneside]{book}
\pagestyle{plain}
\bibliographystyle{ieeetr}

% Variables
\newif\ifpalatino
\newif\ifbookman
\newif\ifdissertation
\newif\ifbookmarks

\palatinofalse
\bookmanfalse
\dissertationfalse
\bookmarksfalse

% Class options
\DeclareOption{palatino}{
	\palatinotrue
}

\DeclareOption{bookman}{
	\bookmantrue
}

\DeclareOption{dissertation}{
	\dissertationtrue
}

\DeclareOption{bookmarks}{
	\bookmarkstrue
}

\ProcessOptions \relax

% External packages
\RequirePackage[left=1.5in,right=1in,top=1in,bottom=1in, includefoot]{geometry}		% Margins
\RequirePackage[T1]{fontenc}  % I added this line Jul 17
\RequirePackage{mathptmx}     % I added this line Jul 17
\RequirePackage{graphicx}
\RequirePackage{setspace}
\RequirePackage[explicit]{titlesec}
\RequirePackage{titletoc}
\RequirePackage{tocloft}
\RequirePackage[none]{hyphenat}
\RequirePackage[nonumberlist, nopostdot]{glossaries}
\RequirePackage{booktabs}
\RequirePackage{amsmath}
\RequirePackage{amsfonts}
\RequirePackage{amssymb}
\RequirePackage{indentfirst}
\RequirePackage{caption}
\RequirePackage{subcaption}
\RequirePackage{rotating}
\RequirePackage[all]{nowidow}
\RequirePackage{fancyhdr}
\RequirePackage[english]{babel}
\RequirePackage{blindtext}
\RequirePackage[normalem]{ulem}
\RequirePackage{fmtcount}
\blindmathtrue

\ifbookmarks	
	\RequirePackage[bookmarks, hidelinks, bookmarksopen, bookmarksnumbered]{hyperref}
\fi

% Algorithm package options
\RequirePackage[tworuled,algochapter]{algorithm2e}
\DontPrintSemicolon
\LinesNumbered
\SetAlgoNlRelativeSize{0}
\SetNlSty{textrm}{}{.}
\SetAlgoSkip{noskip}
\setlength{\algomargin}{0.7in}

\makeglossaries

\ifpalatino
	\RequirePackage{palatino}																		% Use Palatino Font
\else
	\ifbookman
		\RequirePackage{bookman}																	% Use Bookman Font
	\fi
\fi


% Preliminary Pages
%
%\newcommand{\GrizzCoverPage} {
%	\pagenumbering{gobble}
%	\clearpage
%	\thispagestyle{empty}
%	\vspace*{10pt}
%	\begin{center}	
%		\begin{large}
%			\vspace{-23pt}\thesistitle\\ \vfill
%			
%			\ifdissertation DISSERTATION \else THESIS \fi FOR THE DEGREE OF \\
%			
%			\MakeUppercase{\degreename}\vfill
%			
%			\MakeUppercase{\studentname}\vfill
%			
%			OAKLAND UNIVERSITY\vspace{32pt}
%			
%			\the\year
%		\end{large}
%	\end{center}
%	\newpage
%	\pagenumbering{roman}
%}

\newcommand{\GrizzTitlePage}{
	\thispagestyle{empty}
	%\clearpage
	\begin{center}
	\thesistitle\vspace{4pc}
	
	by\vspace{4pc}

	\MakeUppercase{\studentname}\vspace{4pc}
	
	A \ifdissertation dissertation \else thesis \fi submitted in partial fulfillment of the\\
	requirements for the degree of\vspace{4pc}
	
	\MakeUppercase{\degreename}\vspace{4pc}
	
	\the\year\vspace{4pc}
	
	Oakland University\\
	Rochester, Michigan\vspace{6pc}
	\end{center}
	
\noindent \ifdissertation Doctoral \else Thesis \fi Advisory Committee: \vspace*{\baselineskip}

\noindent\CommitteeChair, Ph.D., Chair \\
\noindent\AdvisorOne, Ph.D. \\
\noindent\AdvisorTwo, Ph.D. \\
%\noindent\AdvisorThree, Ph.D. \\
%\noindent\AdvisorFour, Ph.D. \\
%\noindent\AdvisorFive, Ph.D.
\newpage
}

\newcommand{\GrizzCopyrightPage}{
	\thispagestyle{plain}
	
  \vspace*{\fill}
  \begin{center}
  	\copyright\ by \studentname, \the\year \\
		All rights reserved
  \end{center}
  \vspace*{\fill}
  \newpage
}

\newcommand{\GrizzDedicationPage}{
	\thispagestyle{plain}
	\doublespacing
	\vspace*{\fill}
	\begin{center}
	\begin{minipage}{4in}
	\centering
	\textit{\input{Preface/dedication}}
	\end{minipage}
	\end{center}
	\vspace*{\fill}
	\singlespacing
	\newpage
}

\newcommand{\GrizzAcknowledgementsPage}{
	\thispagestyle{plain}
	\begin{center}
		\vspace*{-0.07in}
		{ACKNOWLEDGMENTS}\vspace{2pc}
		\phantomsection
		\addcontentsline{toc}{chapter}{ACKNOWLEDGMENTS}	
	\end{center}	
	
	\doublespacing
	\input{Preface/acknowledgements}

	
	\singlespacing
	\vspace{3\baselineskip}
	%\hfill \makebox[0.5\linewidth]{\studentname\hfill} %Commented out 12/18/2019  
	\hfill \makebox[0.3\linewidth]{\studentname\hfill} %Commented out 12/18/2019  %reinstated  with .3 2/24/2020

	\newpage
}

\newcommand{\GrizzAbstractPage}{
	\pagestyle{plain}	
	\begin{center}
		\vspace*{-0.07in}
		{ABSTRACT}\vspace{2pc}
		\phantomsection
		\addcontentsline{toc}{chapter}{ABSTRACT}	
		
		\thesistitle \vspace{1pc}
		
		by \vspace{1pc}
		
		\studentname \vspace{1pc}
	\end{center}
	
	\noindent Adviser: \CommitteeChair, Ph.D.\vspace{2pc}
			
	\doublespacing
	\input{Preface/abstract}
	
	\singlespacing
	\newpage
}

% Indentation
%\RaggedRight
%\raggedright
%\justify
\setlength{\parindent}{0.5in}

% Heading formatting
\titlespacing{\chapter}{0pt}{-3pc}{0pc}

\titleformat
	{\section}
	{\normalfont\filcenter}
	{}
	{0em}
	{\thesection~ \uline{#1}}
	[\vspace*{-1pc}]


%\newcommand{\subsectionformat}{
\titleformat
	{\subsection}
	{\normalfont\filright}
	{\thesubsection~\uline{#1}}
	{0em}
	{\vspace*{-0.5pc}}	
%}

\titleformat
	{\subsubsection}[runin]
	{\normalfont}
	{\thesubsubsection~}
	{0em}
	{\uline{#1}}	

% add section numbering to the 4th level
\setcounter{secnumdepth}{4}

% Equation formatting
%\DeclareMathSizes{12}{12}{10}{10}

% Table of contents
\renewcommand{\contentsname}{TABLE OF CONTENTS}
\setlength{\cftbeforetoctitleskip}{-7.5pt}
\setlength{\cftaftertoctitleskip}{0.38in}
\renewcommand{\cfttoctitlefont}{\hfill\normalfont\MakeUppercase}
\renewcommand{\cftaftertoctitle}{\hfill}
\cftsetpnumwidth{0in}
\cftsetrmarg{2in}

% Chapter formatting in TOC
\renewcommand{\cftchapindent}{0pt}
\renewcommand{\cftchapnumwidth}{0pt}
\renewcommand{\cftchappresnum}{\MakeUppercase{chapter}~}
\renewcommand{\cftchapaftersnumb}{\nopagebreak \\}
\renewcommand{\cftchapfont}{\normalfont}
\renewcommand{\cftchappagefont}{\normalfont}
\renewcommand{\cftbeforechapskip}{1pc}

\makeatletter
\pretocmd{\@chapter}{\begingroup \renewcommand{\thechapter}{\NUMBERstring{chapter}}}{}{}
\apptocmd{\@chapter}{\endgroup}{}{}
\makeatother

% Section formatting in TOC
\renewcommand{\cftsecindent}{0.88in}
\renewcommand{\cftsecnumwidth}{0.37in}
\renewcommand{\cftsecpresnum}{}
\renewcommand{\cftsecaftersnum}{.~}
\renewcommand{\cftsecfont}{\normalfont}
\renewcommand{\cftsecpagefont}{\normalfont}
\renewcommand{\cftbeforesecskip}{1pc}

% Subsection formatting in TOC
\renewcommand{\cftsubsecindent}{1.19in}
\renewcommand{\cftsubsecnumwidth}{0.55in}
\renewcommand{\cftsubsecpresnum}{}
\renewcommand{\cftsubsecaftersnum}{.}
\renewcommand{\cftsubsecfont}{\normalfont}
\renewcommand{\cftsubsecpagefont}{\normalfont}
\renewcommand{\cftbeforesubsecskip}{1pc}
  
% List of figures 
\renewcommand{\listfigurename}{LIST OF FIGURES}
\renewcommand{\cftloftitlefont}{\hfill\normalfont\MakeUppercase}
\renewcommand{\cftafterloftitle}{\hfill}
\setlength{\cftbeforeloftitleskip}{-0.07in}
\setlength{\cftafterloftitleskip}{0.55in}
\renewcommand{\cftdot}{}
\renewcommand{\cftfigpresnum}{Figure }
\renewcommand{\cftfigindent}{0pc}
\renewcommand{\cftfignumwidth}{1.0in}
\renewcommand{\cftfigafterpnum}{\vspace{12pt}}

% List of tables
\renewcommand{\listtablename}{LIST OF TABLES}
\renewcommand{\cftlottitlefont}{\hfill\normalfont\MakeUppercase}
\renewcommand{\cftafterlottitle}{\hfill}
\setlength{\cftbeforelottitleskip}{-0.1in}
\setlength{\cftafterlottitleskip}{0.55in}
\renewcommand{\cftdot}{}
\renewcommand{\cfttabpresnum}{Table }
\renewcommand{\cfttabindent}{0pc}
\renewcommand{\cfttabnumwidth}{1in}
\renewcommand{\cfttabafterpnum}{\vspace*{12pt}}

% Redefine chapter to not put extra line in lof and lot
\def\@chapter[#1]#2{\ifnum \c@secnumdepth >\m@ne
                       \if@mainmatter
                         \refstepcounter{chapter}%
                         \typeout{\@chapapp\space\thechapter.}%
                         \addcontentsline{toc}{chapter}%
                                   {\protect\numberline{\thechapter}#1}%
                       \else
                         \addcontentsline{toc}{chapter}{#1}%
                       \fi
                    \else
                      \addcontentsline{toc}{chapter}{#1}%
                    \fi
                    \chaptermark{#1}%
%						These lines are commented out to not put space                    
%                    \addtocontents{lof}{\protect\addvspace{10\p@}}
%                    \addtocontents{lot}{\protect\addvspace{10\p@}}
                    \if@twocolumn
                      \@topnewpage[\@makechapterhead{#2}]%
                    \else
                      \@makechapterhead{#2}%
                      \@afterheading
                    \fi}

% Glossary
\newglossarystyle{grizz_glossary}{
\renewcommand{\glossaryname}{\vspace*{0.2in}LIST OF ABBREVIATIONS}
 % put the glossary in the tabular environment:  
 \renewenvironment{theglossary}%  
   {\noindent\begin{longtable}{p{1.13in}p{4.37in}}}{\end{longtable}}%  
 % have nothing after \begin{theglossary}:  
 \renewcommand*{\glossaryheader}{}%  
 % have nothing between glossary groups:  
 \renewcommand*{\glsgroupheading}[1]{}%  
 \renewcommand*{\glsgroupskip}{}%  
 
 \renewcommand*{\glossaryentryfield}[5]{  
 		\glstarget{##1}{##2}\hfill & ##3\hfill \vspace{12pt}\\
 }   
}

% Fancy header styles

\addtolength{\textheight}{-0.05in}
\fancypagestyle{tocstyle}{
\fancyhf{}
\lhead{}
\rhead{}
\chead{TABLE OF CONTENTS---Continued}
\cfoot{\thepage}
\addtolength{\topmargin}{0.57in}
\addtolength{\headsep}{0.28in}
\addtolength{\textheight}{-0.85in}
\renewcommand{\headrulewidth}{0pt}
}

\fancypagestyle{lofstyle}{
\fancyhf{}
\lhead{}
\rhead{}
\chead{LIST OF FIGURES---Continued}
\cfoot{\thepage}
\addtolength{\topmargin}{0.57in}
\addtolength{\headsep}{0.28in}
\addtolength{\textheight}{-0.85in}
\renewcommand{\headrulewidth}{0pt}
}

\fancypagestyle{lotstyle}{
\fancyhf{}
\lhead{}
\rhead{}
\chead{LIST OF TABLES---Continued}
\cfoot{\thepage}
\addtolength{\topmargin}{0.57in}
\addtolength{\headsep}{0.28in}
\addtolength{\textheight}{-0.85in}
\renewcommand{\headrulewidth}{0pt}
}

\fancypagestyle{loastyle}{
\fancyhf{}
\lhead{}
\rhead{}
\chead{LIST OF ABBREVIATIONS---Continued}
\cfoot{\thepage}
\addtolength{\topmargin}{0.57in}
\addtolength{\headsep}{0.28in}
\addtolength{\textheight}{-0.85in}
\renewcommand{\headrulewidth}{0pt}
}

\fancypagestyle{normal}{
\fancyhf{}
\lhead{}
\rhead{}
\chead{}
\cfoot{\thepage}
\addtolength{\topmargin}{-0.57in}
\addtolength{\headsep}{-0.28in}
\addtolength{\textheight}{0.85in}
\renewcommand{\headrulewidth}{0pt}
}

 % Figure placement spacing
\setlength{\intextsep}{0.75in}
\setlength{\textfloatsep}{0.75in}
\setlength{\floatsep}{0.75in}
\makeatletter
\setlength{\@fpsep}{\floatsep}
\makeatother

% References
\renewcommand{\bibname}{\vspace*{36pt}REFERENCES}
 
% Appendices
\newcommand{\StartGrizzAppendices}{
	\setcounter{chapter}{0}
	\renewcommand\thefigure{\Alph{chapter}.\arabic{figure}}	
	\renewcommand\theequation{\Alph{chapter}.\arabic{equation}}
	\renewcommand\thesection{\Alph{chapter}.\arabic{section}}
}

\newcounter{appendixnumber}
\newcounter{appendixsectionnumber}[appendixnumber]
\newcounter{appendixequationnumber}[appendixnumber]
\newcounter{appendixfigurenumber}[appendixnumber]
\renewcommand{\theappendixnumber}{\Alph{appendixnumber}}

\newcommand{\GrizzAppendix}[1]{
\stepcounter{chapter}
\setcounter{equation}{0}
\setcounter{figure}{0}
\setcounter{section}{0}
\refstepcounter{appendixnumber}
\newpage
\phantomsection
\addcontentsline{toc}{section}{\protect\makebox[0.3in][l]{\Alph{appendixnumber}.} #1}
\vspace*{\fill}\begin{center} APPENDIX~\Alph{appendixnumber} \\ \MakeUppercase{#1}\end{center} \vspace*{\fill}\newpage
} 

% Custom section to get A.x section numbering
\newcommand{\GrizzAppendixSection}[1]{
\refstepcounter{appendixsectionnumber}
\refstepcounter{section}
\phantomsection
\addcontentsline{toc}{subsection}{\protect\makebox[0.45in][l]{\Alph{appendixnumber}.\arabic{appendixsectionnumber}.} #1}
\section*{#1}
}


\endinput
