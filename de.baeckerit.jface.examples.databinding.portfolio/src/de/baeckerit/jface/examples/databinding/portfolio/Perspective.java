package de.baeckerit.jface.examples.databinding.portfolio;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.baeckerit.jface.examples.databinding.portfolio.view.positions.PositionsViewPart;
import de.baeckerit.jface.examples.databinding.portfolio.view.securities.SecuritiesViewPart;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);

		IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, 0.5f, editorArea);
		leftFolder.addView(SecuritiesViewPart.ID);

		IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, 0.66f, editorArea);
		rightFolder.addView(PositionsViewPart.ID);
	}
}
