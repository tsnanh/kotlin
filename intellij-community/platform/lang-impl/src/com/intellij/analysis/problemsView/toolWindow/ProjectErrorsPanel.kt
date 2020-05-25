// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.analysis.problemsView.toolWindow

import com.intellij.openapi.actionSystem.ToggleOptionAction.Option
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.problems.ProblemListener

internal class ProjectErrorsPanel(project: Project, state: ProblemsViewState)
  : ProblemsViewPanel(project, state), ProblemListener {

  private val watchers = mutableMapOf<VirtualFile, HighlightingWatcher>()
  private val root = Root(this)

  init {
    treeModel.root = root
    project.messageBus.connect(this)
      .subscribe(ProblemListener.TOPIC, this)
  }

  companion object {
    private val LOG = Logger.getInstance(ProjectErrorsPanel::class.java)
  }

  public override fun getDisplayName() = ProblemsViewBundle.message("problems.view.project")
  public override fun getShowErrors(): Option? = null
  public override fun getShowWarnings(): Option? = null
  public override fun getShowInformation(): Option? = null
  public override fun getSortFoldersFirst(): Option? = null

  override fun problemsAppeared(file: VirtualFile) {
    LOG.debug("problemsAppeared: ", file)
    synchronized(watchers) {
      val watcher = watchers.computeIfAbsent(file) { HighlightingWatcher(root, it) }
      Disposer.register(root, watcher)
    }
  }

  override fun problemsChanged(file: VirtualFile) {
    LOG.debug("problemsChanged: ", file)
  }

  override fun problemsDisappeared(file: VirtualFile) {
    LOG.debug("problemsDisappeared: ", file)
    synchronized(watchers) {
      val watcher = watchers.remove(file)
      if (watcher != null) {
        Disposer.dispose(watcher)
        root.removeProblems(file)
      }
    }
  }
}
