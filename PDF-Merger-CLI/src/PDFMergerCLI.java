/*
 * Created By Joshua Viles
 * Created on: 11 July 2016
 *
 * Program Name: PDF-Merger -CL
 *
 * This program by Command Line interface  will prompt for the file paths of two PDF's
 * and the destination file with file path and outputs a merged PDF of the two PDFs that were inputed
 *
 * Copyright (C) 2016  Joshua Viles
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.File;
import java.util.Scanner;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.utils.*;

public class PDFMergerCLI
{
    public static void main(String args[]) throws IOException, java.io.IOException
    {
        //Takes in Keyboard Input
        Scanner stdIn = new Scanner(System.in);

        //Initializing three variables to be used for file paths.
        String src1 = "";
        String src2 = "";
        String dest = "";

        //Prompts for the file path to the first PDF to be merged (Front Page)
        System.out.print("Please enter the file path to the first pdf: ");
        src1 = stdIn.nextLine().toString();
        //System.out.println(src1); //this was a test statement

        //Prompts for the file path to the second PDF to be merged (Back Page)
        System.out.print("Please enter the file path to the second pdf: ");
        src2 = stdIn.nextLine().toString();
        //System.out.println(src2); //this was a test statement

        //Prompts for the file path to the destination of the merged document
        //must include filename of the merged document
        System.out.print("Please enter the file path to the destination pdf: ");
        dest = stdIn.nextLine().toString();
        //System.out.println(dest); //this was a test statement

        File file = new File(dest);
        file.getParentFile().mkdirs();

        //Initialize PDF document with output intent
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfMerger merger = new PdfMerger(pdf);

        //Add pages from the first document
        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(src1));
        merger.merge(firstSourcePdf, 1, firstSourcePdf.getNumberOfPages());

        //Add pages from the second pdf document
        PdfDocument secondSourcePdf = new PdfDocument(new PdfReader(src2));
        merger.merge(secondSourcePdf, 1, secondSourcePdf.getNumberOfPages());

        firstSourcePdf.close(); //closes first sourced pdf
        secondSourcePdf.close(); //closes second sourced pdf
        pdf.close(); //closes the merged pdf
        stdIn.close(); //closes the keyboard input module

        System.out.println("Merge Completed!");
    }
}
