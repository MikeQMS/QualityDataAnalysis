/*
    <Q-Data Analytics tool with xlsx import and MySQL DB>
    Copyright (C) 2022-  MikeQMS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.qdata.model;

public enum SchadenCode {
    ZZZZ    ("No DataSet in DB"),
    AB01    ("Abdichtung unvollständig/fehlt"),
    AB02    ("Abdichtung falsch"),
    AB03    ("Blasenbildung"),
    AB04    ("Abdichtung beschädigt"),
    BE01	("Verbogen"),
    BE02	("Riss / Spalt"),
    BE03	("Delle / Beule"),
    BE04	("Kratzer / Kerbe"),
    BE05	("Spanabdruck"),
    BE06	("Scheuerstelle"),
    BE07	("Querschläger"),
    BO01	("Doppelte Bohrung / Dopppelloch"),
    BO02	("Langloch"),
    BO03	("Anbohrung"),
    BO04	("Bohrung zu groß"),
    BO05	("Falsche Bohrungsposition"),
    BO06	("Fehlerhafte Senkung"),
    BO07	("Kanten-/ Lochabstand"),
    BO08	("fehlende Bohrung"),
    BO09	("Falscher Bohrungswinkel"),
    BO10	("Bohrungsdurchmesser zu klein"),
    BO11	("Beschädigte Bohrungsfläche/-kante"),
    BO12	("Inkorrektes Gewinde"),
    DO01	("Dokumentation fehlt"),
    DO02	("Inkorrekter Inhalt"),
    DO03	("unlesbare/beschädigte Kennzeichnung"),
    DO04	("Position der Kennzeichnung"),
    DO05	("fehlende/falsche Kennzeichnung"),
    DO07	("Kennzeichnungsverfahren"),
    DO08	("Design Query"),
    EL01	("Messwert außer Toleranz"),
    EL02	("Fehlende inkorrekte Masseversiegelung"),
    EL03	("elektrische Leitung/Stecker beschädigt"),
    FO01	("F.O.D. gefunden ohne Beschädigung"),
    FO02	("F.O.D. gefunden mit Beschädigung"),
    MA01	("Abweichende geometrische Form"),
    MA02	("Haltbarkeit abgelaufen"),
    MA03	("Wärmebehandlung"),
    MA04	("Musterprüfung"),
    MA05	("Materialeigenschaften"),
    MA06	("Verunreinigung"),
    MA07	("Kit unvollständig"),
    MA08	("Fehlteil"),
    MO01	("Spalt/Spiel zwischen Bauteilen"),
    MO02	("Kollision zwischen Bauteilen"),
    MO03	("Falsches Bauteil eingebaut"),
    MO04	("Versatz"),
    MO05	("Bauteil falsch eingebaut"),
    MO06	("Bauteil nicht eingebaut"),
    MO07	("Bauteil nicht einbaubar"),
    OS01	("Oberflächenschutz unvollständig/fehlt"),
    OS02	("Oberflächenschutz falsch"),
    OS03	("Oberflächenschutz nicht ausgehärtet"),
    OS04	("Flecken / Abdrücke"),
    OS05	("Lack-/ Farbablösung"),
    OS06	("Einschlüsse"),
    OS07	("Spritznebel"),
    OS08	("Rauheit"),
    OS09	("Schichtdicke außer Toleranz"),
    OS10	("Lackläufer"),
    OS11	("Orangenhaut"),
    OS12	("Glanzgrad"),
    OS13	("Farbton"),
    VB01	("Falsches Verbindungsteil"),
    VB02	("Verbindugselement fehlt"),
    VB03	("Schließkopf fehlerhaft"),
    VB04	("Setzkopf fehlerhaft"),
    VB05	("Fremdkörper zwischen Bauteil/Nietkopf"),
    VB06	("Über-/ Unterstand"),
    VB07	("Verbindungselemen Einzug/Verformung"),
    VB08	("Verbindungsteil unvollständig"),
    VB09	("Verbindungselement seitenverkehrt"),
    VB10	("Verbindungselement nicht fest");



    public final String label;

    SchadenCode(String label) {
        this.label = label;
    }
}
